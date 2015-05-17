package jp.peranikov.puhaar.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Yuto on 2015/05/04.
 */
public class Photo implements Serializable {
    private Bitmap image;
    private String comment;
    private Integer id;
    private String url;
    private User user;

    public Integer id() {
        return this.id;
    }

    public Photo id(Integer id) {
        this.id = id;
        return this;
    }

    public Bitmap image() {
        return this.image;
    }

    public Photo image(Bitmap image) {
        this.image = image;
        return this;
    }

    public String comment() {
        return this.comment;
    }

    public Photo comment(String comment) {
        this.comment = comment;
        return this;
    }

    public String url() {
        return this.url;
    }

    public Photo url(String url) {
        this.url = url;
        return this;
    }

    public User user() {
        return this.user;
    }

    public Photo user(User user) {
        this.user = user;
        return this;
    }
}
