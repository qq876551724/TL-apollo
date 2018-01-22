package com.apollo.entity.order;

import lombok.Data;

import java.io.Serializable;

/**
 * com.apollo.entity.order.Order <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : 876551724@qq.com
 */
@Data
public class Order implements Serializable {
    String id;
    String no;
    String createTime;
    String state;
}
