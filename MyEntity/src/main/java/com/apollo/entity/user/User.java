package com.apollo.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description :
 * @Author : tianlei
 * @Create : 2017/10/27.
 * @E-mail : 876551724@qq.com
 */
public class User implements Serializable {
    String name;
    String age;
    String sex;
    String pwd;

    @Override
    public String toString(){
        return "{\"name\":\"" + this.name + "\"," +
                "\"age\":\"" + this.age + "\"," +
                "\"pwd\":\"" + this.pwd + "\"," +
                "\"sex\":\"" + this.sex + "\"}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
