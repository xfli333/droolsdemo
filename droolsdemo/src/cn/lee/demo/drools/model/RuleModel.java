package cn.lee.demo.drools.model;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-16
 * Time: 下午1:04
 */
public class RuleModel extends Model{
    private String ruleName;

    private String ruleType;

    private String ruleContent;

    private String customizeRule="";

    private boolean firstRole=false;

    private boolean actived;

    public RuleModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleModel ruleModel = (RuleModel) o;

        if (ruleName != null ? !ruleName.equals(ruleModel.ruleName) : ruleModel.ruleName != null) return false;
        if (ruleType != null ? !ruleType.equals(ruleModel.ruleType) : ruleModel.ruleType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruleName != null ? ruleName.hashCode() : 0;
        result = 31 * result + (ruleType != null ? ruleType.hashCode() : 0);
        return result;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public boolean isFirstRole() {
        return firstRole;
    }

    public void setFirstRole(boolean firstRole) {
        this.firstRole = firstRole;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public String getCustomizeRule() {
        return customizeRule;
    }

    public void setCustomizeRule(String customizeRule) {
        this.customizeRule = customizeRule;
    }
}

