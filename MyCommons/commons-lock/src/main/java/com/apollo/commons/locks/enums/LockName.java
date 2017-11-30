package com.apollo.commons.locks.enums;

/**
 * LockName <br>
 *
 * @Description :区分不同的锁
 * @Author : tianlei
 * @Create : 2017/11/6.
 * @E-mail : tianlei@simpletour.com
 */
public enum LockName {
    InventoryLock("库存锁标志","inventory"),
    OrderLock("订单锁标志","order")
    ;

    private String name;
    private String value;

    private LockName( String name , String value ){
        this.name = name ;
        this.value = value ;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
