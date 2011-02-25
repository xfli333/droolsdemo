package cn.lee.demo.drools.action;


import cn.lee.demo.drools.model.HospitalRecords;
import cn.lee.demo.drools.rule.DroolsUtils;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.lee.demo.drools.model.RuleModel;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-15
 * Time: 下午9:59
 */
@Controller
@RequestMapping("/hos")
public class HospitalRecordsAction extends BaseAction {
    private  String ruleContent="";
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveOrUpdate(HospitalRecords hospitalRecords, HttpServletRequest request) {
        ruleContent=initActionRule("rule1");
        System.out.println(ruleContent);
        final StatefulKnowledgeSession ksession = DroolsUtils.createKnowledgeSession(ruleContent);
        ksession.insert(hospitalRecords);
        ksession.fireAllRules();
        ksession.dispose();
        if (hospitalRecords.getWrongMsg() == null || "".equals(hospitalRecords.getWrongMsg())) {
            this.baseManager.store(hospitalRecords);
        }else{
            hospitalRecords.setRuleMsg("保存失败,不符合规则");
        }
        request.setAttribute("hospitalRecords", hospitalRecords);
        return "hospitalRecords/hospitalRecordsPro";
    }

    @RequestMapping(value = "/new")
    public String newUser(HttpServletRequest request) {
        HospitalRecords hospitalRecords = new HospitalRecords();
        request.setAttribute("hospitalRecords", hospitalRecords);
        return "hospitalRecords/hospitalRecordsPro";
    }



}
