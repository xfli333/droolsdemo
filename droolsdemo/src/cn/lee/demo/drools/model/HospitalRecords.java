package cn.lee.demo.drools.model;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-15
 * Time: 下午9:26
 */
public class HospitalRecords extends DroolsModel{
    public String identityCard;
    public String name;
    public String sex;
    public String menstrual;
    public String fertility;
    public String others;

    public HospitalRecords() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HospitalRecords that = (HospitalRecords) o;

        if (identityCard != null ? !identityCard.equals(that.identityCard) : that.identityCard != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return identityCard != null ? identityCard.hashCode() : 0;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMenstrual() {
        return menstrual;
    }

    public void setMenstrual(String menstrual) {
        this.menstrual = menstrual;
    }

    public String getFertility() {
        return fertility;
    }

    public void setFertility(String fertility) {
        this.fertility = fertility;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "HospitalRecords{" +
                "identityCard='" + identityCard + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", menstrual='" + menstrual + '\'' +
                ", fertility='" + fertility + '\'' +
                ", others='" + others + '\'' +
                '}';
    }
}
