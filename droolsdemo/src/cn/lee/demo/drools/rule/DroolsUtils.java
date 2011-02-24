package cn.lee.demo.drools.rule;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.event.rule.DebugAgendaEventListener;
import org.drools.event.rule.DebugWorkingMemoryEventListener;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-15
 * Time: 下午11:55
 */
public class DroolsUtils {

    public static StatefulKnowledgeSession createKnowledgeSession(String ruleContent) {
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
                .newKnowledgeBuilder();

        // this will parse and compile in one step

//        kbuilder.add(ResourceFactory.newClassPathResource(ruleFile,
//                DroolsUtils.class), ResourceType.DRL);

//        InputStream inputStream = DroolsUtils.class.getClassLoader().getResourceAsStream(ruleFile);
        InputStream inputStream = String2InputStream(ruleContent);
        kbuilder.add(ResourceFactory.newInputStreamResource(inputStream), ResourceType.DRL);


        // Check the builder for errors
        if (kbuilder.hasErrors()) {
            System.out.println(kbuilder.getErrors().toString());
            throw new RuntimeException("Unable to compile  rule");
        }

        // get the compiled packages (which are serializable)
        final Collection<KnowledgePackage> pkgs = kbuilder
                .getKnowledgePackages();

        // add the packages to a knowledgebase (deploy the knowledge packages).
        final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(pkgs);

        final StatefulKnowledgeSession ksession = kbase
                .newStatefulKnowledgeSession();
        ksession.setGlobal("list", new ArrayList<Object>());
        return ksession;
    }

    private static InputStream String2InputStream(String str) {
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

}
