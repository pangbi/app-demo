所需环境：jdk7,tomcat7,redis,zookeeper,mysql

启动顺序：app-redis -> app-remote -> app-web

资源配置:redis配置，mysql配置,logback日志级别配置 由app-resource-manager统一发布至zookeeper节点
        其他各app从zk节点获取配置信息


