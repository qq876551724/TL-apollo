package com.apollo.commons.mq.utils.enums;

/**
 * com.apollo.commons.mq.utils.enums.KeyInfo <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/27.
 * @E-mail : 876551724@qq.com
 */
public enum KeyInfo {
    NEW_ORDER_SYNC("tl.queue.new", "NEW.ORDER");

    private String key;
    private String action;

    private KeyInfo(String key, String action) {
        this.key = key;
        this.action = action;
    }

    public String getKey() {
        return this.key;
    }

    public String getAction() {
        return this.action;
    }
}
