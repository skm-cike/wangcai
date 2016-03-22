package org.test.database.redis;

import java.io.Serializable;

/**
 * Created by 陆华 on 15-12-22 下午2:50
 */
class User implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;
    private String name;
    private String pwd;
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}