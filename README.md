TL-apollo为分布式系统1.0版本代号，目前包括如下系统：

# 系统说明
1. MyController 服务消费者系统（war）
2. MyInterface 接口系统（POM）
3. MyService 服务提供者系统（assembly）
4. MyEntity 实体类(POM)
5. MyCommons  基础开发组件 （POM）  
    commons-captcha 验证码组件  
    commons-data 数据组件  
    commons-lock 分布式锁组件  
    commons-mq 消息队列组件  
    commons-redis 分布式缓存组件  
    commons-shiro 安全框架组件  
    commons-utils 通用工具组件  


# 发布

## 不包含web项目的deploy
配置环境变量  
CATALINA_HOME 指定dubbo所在tomcat目录  
ZOOKEEPER_HOME 指定zookeeper所在目录  

服务提供者 MyService 打包后  
1.运行bin下 startDubbo.cmd启动zookeeper及tomcat  
2.运行bin下 start.bat或start.sh启动服务  

