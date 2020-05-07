package com.macinternetservices.sablebusinessdirectory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAuthPOJO {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("wp_user_id")
    @Expose
    private Integer wpUserId;
    @SerializedName("cookie")
    @Expose
    private String cookie;
    @SerializedName("user_login")
    @Expose
    private String userLogin;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getWpUserId() {
        return wpUserId;
    }

    public void setWpUserId(Integer wpUserId) {
        this.wpUserId = wpUserId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

}