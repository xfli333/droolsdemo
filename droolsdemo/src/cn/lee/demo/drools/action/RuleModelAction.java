package cn.lee.demo.drools.action;

import cn.lee.demo.drools.model.RuleModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-16
 * Time: 下午1:35
 */
@Controller
@RequestMapping("/rule")
public class RuleModelAction extends BaseAction {
    @RequestMapping(value = "/new")
    public String newUser(HttpServletRequest request) {
        RuleModel ruleModel = new RuleModel();
        request.setAttribute("ruleModel", ruleModel);
        return "ruleModel/ruleModelPro";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveOrUpdate(RuleModel ruleModel, HttpServletRequest request) {
        if("on".equals(request.getParameter("firstRole"))){
            ruleModel.setFirstRole(true);
        }
         if("on".equals(request.getParameter("actived"))){
            ruleModel.setActived(true);
        }
        this.baseManager.store(ruleModel);
        request.setAttribute("ruleModel", ruleModel);
        return "ruleModel/ruleModelPro";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listAll(RuleModel ruleModel, HttpServletRequest request) {
        List<RuleModel> rms=(List<RuleModel>)this.baseManager.loadAll(RuleModel.class);
        request.setAttribute("rms", rms);
        return "ruleModel/ruleModelList";
    }

    @RequestMapping(value = "/enable")
    public String enableRule( HttpServletRequest request,String id) {
        RuleModel rm=this.baseManager.load(RuleModel.class,Long.valueOf(id));
        rm.setActived(true);
//        String content=rm.getRuleContent();
//        String test=content.substring(content.indexOf("HospitalRecords(")+16,content.indexOf("&& menstrual !=null"));
//        rm.setRuleContent(content.replace(test,"sex==\"女\""));
//        System.out.println(test);
        this.baseManager.store(rm);
//        String hql="update RuleModel rm set rm.actived = ? where rm.id = ?";
//        this.baseManager.execute(hql,true,Long.valueOf(id));
        List<RuleModel> rms=(List<RuleModel>)this.baseManager.loadAll(RuleModel.class);
        request.setAttribute("rms", rms);
        return "ruleModel/ruleModelList";
    }

    @RequestMapping(value = "/disable")
    public String disableRule( HttpServletRequest request,String id) {
        String hql="update RuleModel rm set rm.actived = ? where rm.id = ?";
        this.baseManager.execute(hql,false,Long.valueOf(id));
        List<RuleModel> rms=(List<RuleModel>)this.baseManager.loadAll(RuleModel.class);
        request.setAttribute("rms", rms);
        return "ruleModel/ruleModelList";
    }

    @RequestMapping(value = "/edit")
    public String editRule( HttpServletRequest request,String id) {
        RuleModel ruleModel=this.baseManager.load(RuleModel.class,Long.valueOf(id));
        if(ruleModel.isFirstRole()){
             List<RuleModel> rms=(List<RuleModel>)this.baseManager.loadAll(RuleModel.class);
             request.setAttribute("rms", rms);
             return "ruleModel/ruleModelList";
        }
        request.setAttribute("ruleModel", ruleModel);
        return "ruleModel/ruleModelEdit";
    }

    @RequestMapping(value = "/update")
    public String editRule(HttpServletRequest request,String id,String ruleField,String symbol,String ruleValue) {
        System.out.println(id);
        System.out.println(ruleField+symbol+ruleValue);
        RuleModel ruleModel=this.baseManager.load(RuleModel.class,Long.valueOf(id));
        ruleModel.setCustomizeRule(ruleField+symbol+ruleValue);
        this.baseManager.store(ruleModel);
        request.setAttribute("ruleModel", ruleModel);
        return "ruleModel/ruleModelEdit";
    }
}
