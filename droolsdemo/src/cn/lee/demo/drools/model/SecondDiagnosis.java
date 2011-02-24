package cn.lee.demo.drools.model;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-16
 * Time: 上午9:21
 */
public class SecondDiagnosis extends DroolsModel{

    public String identityCard;
    public String name;
    public String parts;

    public FirstDiagnosis fd;

    public SecondDiagnosis() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondDiagnosis that = (SecondDiagnosis) o;

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

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public FirstDiagnosis getFd() {
        return fd;
    }

    public void setFd(FirstDiagnosis fd) {
        this.fd = fd;
    }
}
