# VisualVM

1. 下载[visualVM](https://visualvm.github.io/download.html)

2. 远程监控应用程序的运行

   1. JMX

      ```shell
      java  \
      -Djava.rmi.server.hostname=192.168.115.54 \   # 远程主机的ip地址
      -Dcom.sun.management.jmxremote \
      -Dcom.sun.management.jmxremote.port=10099 \ 	# 监听的端口
      -Dcom.sun.management.jmxremote.authenticate=false \
      -Dcom.sun.management.jmxremote.ssl=false \
      -jar target/api-1.0.jar &
      ```

      在别的电脑上打开visualvm后，点击远程连接，并输入ip地址和端口号，勾选`非ssl`即可连接。

   2. jstatd

      ```shell
      cd ../jdk1.8.0_181.jdk/Contents/Home/jre/lib/security
      sudo vi java.policy
      # 最后一行添加
      # permission java.security.AllPermission;
      
      cd ../jdk1.8.0_181.jdk/Contents/Home/jre/bin
      jstatd -J-Djava.security.policy=all.policy -p 1099 &
      ```

      在别的电脑上打开visualvm后，点击远程连接，并输入ip地址和端口号，勾选`非ssl`即可连接。





#### 参考资料

* [使用VisualVM远程监控JVM Linux服务器配置方法](<https://www.linuxidc.com/Linux/2017-01/139409.htm>)
* [java11 设置visualVM远程连接](<https://ericdraken.com/profile-remote-java-apps-jdk-11-visualvm/>)