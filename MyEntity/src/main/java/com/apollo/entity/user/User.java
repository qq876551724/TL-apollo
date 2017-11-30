package com.apollo.entity.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description :
 * @Author : tianlei
 * @Create : 2017/10/27.
 * @E-mail : tianlei@simpletour.com
 */
@Data
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
}
