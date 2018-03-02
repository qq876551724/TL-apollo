package com.apollo.entity;



import java.util.ArrayList;
import java.util.List;

/**
 * com.apollo.entity.JsonObjectResult <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
public class JsonObjectResult<T> {
    public JsonObjectResult(){
        success=false;
        list = new ArrayList<T>();
    }
    public String message;
    public boolean success;
    public Object obj;
    public List<T> list ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
