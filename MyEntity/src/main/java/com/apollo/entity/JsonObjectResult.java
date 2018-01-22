package com.apollo.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * com.apollo.entity.JsonObjectResult <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : 876551724@qq.com
 */
@Data
public class JsonObjectResult<T> {
    public JsonObjectResult(){
        success=false;
        list = new ArrayList<T>();
    }
    public String message;
    public boolean success;
    public Object obj;
    public List<T> list ;
}
