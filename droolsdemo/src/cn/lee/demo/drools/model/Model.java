package cn.lee.demo.drools.model;

/**
 * Created by IntelliJ IDEA.
 * User: Lee
 * Date: 11-2-15
 * Time: 下午9:27
 */
public class Model {
    public long id;
    public long version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
