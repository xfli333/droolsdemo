package cn.lee.demo.drools.action;

import cn.lee.demo.drools.model.FirstDiagnosis;
import cn.lee.demo.drools.model.HospitalRecords;
import cn.lee.demo.drools.model.SecondDiagnosis;
import cn.lee.demo.drools.rule.DroolsUtils;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-16
 * Time: 上午9:29
 */
@Controller
@RequestMapping("/diagnosis")
public class SecondDiagnosisAction extends BaseAction {

    private String roleContent = "";

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveOrUpdate(SecondDiagnosis secondDiagnosis, HttpServletRequest request) {
        roleContent = this.initActionRule("rule2");

        FirstDiagnosis fd = new FirstDiagnosis();
        fd.setIdentityCard("id123");
        fd.setName("user name");
        fd.setParts("parts");
        secondDiagnosis.setFd(fd);


        final StatefulKnowledgeSession ksession = DroolsUtils.createKnowledgeSession(roleContent);
        ksession.insert(secondDiagnosis.getFd());
        ksession.insert(secondDiagnosis);
        ksession.fireAllRules();
        ksession.dispose();


        if (secondDiagnosis.getWrongMsg() == null || "".equals(secondDiagnosis.getWrongMsg())) {
            this.baseManager.store(secondDiagnosis);
        } else {
            secondDiagnosis.setRuleMsg("保存失败,不符合规则");
        }
        request.setAttribute("secondDiagnosis", secondDiagnosis);
        return "secondDiagnosis/secondDiagnosisPro";
    }

    @RequestMapping(value = "/new")
    public String newUser(HttpServletRequest request) {
        SecondDiagnosis secondDiagnosis = new SecondDiagnosis();
        FirstDiagnosis fd = new FirstDiagnosis();
        fd.setIdentityCard("id123");
        fd.setName("user name");
        fd.setParts("parts");
        secondDiagnosis.setFd(fd);
        request.setAttribute("secondDiagnosis", secondDiagnosis);
        return "secondDiagnosis/secondDiagnosisPro";
    }
}
