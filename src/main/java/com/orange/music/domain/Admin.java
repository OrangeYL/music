package com.orange.music.domain;

import java.io.Serializable;

/**
 * 管理员实体类
 */
public class Admin implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 管理员账号
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
