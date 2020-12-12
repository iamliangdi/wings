package com.liangdi.wings.authorization.entity;

import java.io.Serializable;

/**
 * @author Liang Di
 * @since 1.0.0
 */
public class Manager implements Serializable {
    // 用户唯一编号
    private Long id;
    // 用户名
    private String username;
    // 用户认证授权凭证
    private String credentials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }
}
