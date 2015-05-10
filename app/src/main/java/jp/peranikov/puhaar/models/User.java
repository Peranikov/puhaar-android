package jp.peranikov.puhaar.models;

/**
 * Created by Yuto on 2015/05/10.
 */
public class User {
    private Integer id;
    private String name;
    private String iconUrl;

    public Integer id() {
        return this.id;
    }

    public User id(Integer id) {
        this.id = id;
        return this;
    }

    public String name() {
        return this.name;
    }

    public User name(String name) {
        this.name = name;
        return this;
    }

    public String iconUrl() {
        return this.iconUrl;
    }

    public User iconUrl(String iconUrl) {
        this.name = iconUrl;
        return this;
    }
}
