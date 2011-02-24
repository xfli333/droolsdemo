package cn.lee.demo.drools.model;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-15
 * Time: 下午9:25
 */
public class DroolsModel extends Model{
    private String ruleMsg="";
    private String wrongMsg="";

    public String getRuleMsg() {
        return ruleMsg;
    }

    public void setRuleMsg(String ruleMsg) {
        this.ruleMsg = ruleMsg;
    }

    public String getWrongMsg() {
        return wrongMsg;
    }

    public void setWrongMsg(String wrongMsg) {
        this.wrongMsg = wrongMsg;
    }
}
