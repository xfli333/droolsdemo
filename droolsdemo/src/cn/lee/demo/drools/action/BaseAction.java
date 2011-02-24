package cn.lee.demo.drools.action;

import cn.lee.demo.drools.model.RuleModel;
import cn.lee.demo.drools.services.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 10-12-14
 * Time: 下午4:12
 */
@Repository("baseAction")
public class BaseAction {
    @Autowired
    protected Manager baseManager;

    protected String initActionRule(String ruleType){
        String ruleContent="";
        String hql="from RuleModel rm where rm.roleType = ? and rm.actived = ?";
        List<RuleModel> rms=this.baseManager.findByQuery(hql,ruleType,true);
        for(RuleModel rm :rms){
            if(rm.isFirstRole()){
                ruleContent=rm.getRoleContent()+ruleContent;
            }else{
                ruleContent+=rm.getRoleContent();
            }
        }
        return ruleContent;
    }
}
