package com.apollo.commons.mq.utils.pojo;

/**
 * com.apollo.commons.mq.utils.pojo.MQLogMessage <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/24.
 * @E-mail : 876551724@qq.com
 */
public class MQLogMessage extends MQMessage {
    private MQCallerInfo callerInfo;
    private MQLogMessage.Level level;
    private String desc;
    private MQBizMessage mqBizMessage;

    public MQLogMessage(MQBizMessage mqBizMessage, MQCallerInfo callerInfo) {
        this.level = MQLogMessage.Level.INFO;
        this.mqBizMessage = mqBizMessage;
        this.callerInfo = callerInfo;
    }

    public MQLogMessage(MQLogMessage.Level level, MQBizMessage mqBizMessage, MQCallerInfo callerInfo) {
        this.level = MQLogMessage.Level.INFO;
        this.level = level;
        this.mqBizMessage = mqBizMessage;
        this.callerInfo = callerInfo;
    }

    public MQLogMessage(MQLogMessage.Level level, MQBizMessage mqBizMessage, MQCallerInfo callerInfo, String desc) {
        this.level = MQLogMessage.Level.INFO;
        this.level = level;
        this.mqBizMessage = mqBizMessage;
        this.callerInfo = callerInfo;
        this.desc = desc;
    }

    public MQCallerInfo getCallerInfo() {
        return this.callerInfo;
    }

    public MQLogMessage.Level getLevel() {
        return this.level;
    }

    public MQBizMessage getMqBizMessage() {
        return this.mqBizMessage;
    }

    public void setMqBizMessage(MQBizMessage mqBizMessage) {
        this.mqBizMessage = mqBizMessage;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static enum Level {
        INFO("信息"),
        DEBUG("调试"),
        WARNING("警告"),
        ERROR("错误");

        private String value;

        private Level(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
