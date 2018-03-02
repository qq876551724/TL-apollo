package com.apollo.entity.order;



import java.io.Serializable;

/**
 * com.apollo.entity.order.Order <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : tianlei@simpletour.com
 */
public class Order implements Serializable {
    String id;
    String no;
    String createTime;
    String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
