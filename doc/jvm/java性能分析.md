# java性能分析

1. jps使用

   显示当前所有的java进程信息

   ```shell
   -q # 只显示pid，不显示别的信息
   -m # 输出传入main方法的参数
   -v # 输出传入jvm的参数
   -V # 输出通过文件传递给jvm的参数
   -l # 输出主类的全名或jar路径
   ```

   ```shell
   jps # 只显示pid和启动的java应用程序名字
   jps -m # 增加了传入的main方法参数
   jps -ml # java命令调用的该应用程序主类的全名和jar路径
   jps -lv # 显示java应用启动时传递进的jvm参数
   ```

   