package com.apollo.commons.locks;



/**
 * DistributedLock <br>
 *
 * @Description :Zookeeper的相关配置
 * @Author : tianlei
 * @Create : 2017/11/6.
 * @E-mail : 876551724@qq.com
 */
public class ZkConfig {

    /**
     * 连接地址
     */
    private String connectString;

    /**
     * Session的过期时间, 如果客户端设置的超时时间不在这个范围，
     * 那么会被强制设置为最大或最小时间。 默认的Session超时时间是在 2 * tickTime ~ 20 * tickTime, 而tickTime默认是(3000)
     */
    private Integer sessionTimeout;

    /**
     * 顺序节点的前缀
     */
    private String seqPrefix;

    /**
     * 基路径
     */
    private String basePath;

    private static final String SEPARATOR = "/";

    public ZkConfig() {
    }

    public ZkConfig(String connectString, Integer sessionTimeout, String seqPrefix, String basePath) {
        this.connectString = connectString;
        this.sessionTimeout = sessionTimeout;
        this.seqPrefix = seqPrefix;
        this.basePath = basePath.endsWith(SEPARATOR) ? basePath : basePath + SEPARATOR;
    }

    public void setBasePath(String basePath) {
        if (basePath == null) {
            return;
        }
        this.basePath = basePath.endsWith(SEPARATOR)
                ? basePath : basePath + SEPARATOR;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getSeqPrefix() {
        return seqPrefix;
    }

    public void setSeqPrefix(String seqPrefix) {
        this.seqPrefix = seqPrefix;
    }

    public String getBasePath() {
        return basePath;
    }

    public static String getSEPARATOR() {
        return SEPARATOR;
    }
}
