# tomcat配置集群

1. redis安装配置

   ```shell
   docker pull redis # 下载redisdocker镜像
   
   docker run --name redis -v .../redis:/data -p 6379:6379 -d redis redis-server --appendonly yes --requirepass "redis" # 启动docker并设置密码为redis
   ```

   

#### 相关信息

* [redis tomcat集群](https://github.com/redisson/redisson/tree/master/redisson-tomcat)
* [Redis-Based Tomcat Session Management](https://dzone.com/articles/redis-based-tomcat-session-management)



#### 参考资料

* [nginx负载均衡搭建及测试](https://www.cnblogs.com/redcoatjk/p/6279225.html)
* [搭建Tomcat集群&通过Redis缓存共享session的一种流行方案](https://segmentfault.com/a/1190000009591087)