package cn.lee.demo.drools.model;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-16
 * Time: 下午1:04
 */
public class RuleModel extends Model{
    private String roleName;

    private String roleType;

    private String roleContent;

    private boolean firstRole=false;

    private boolean actived;

    public RuleModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleModel ruleModel = (RuleModel) o;

        if (roleName != null ? !roleName.equals(ruleModel.roleName) : ruleModel.roleName != null) return false;
        if (roleType != null ? !roleType.equals(ruleModel.roleType) : ruleModel.roleType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleName != null ? roleName.hashCode() : 0;
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        return result;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleContent() {
        return roleContent;
    }

    public void setRoleContent(String roleContent) {
        this.roleContent = roleContent;
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
}

