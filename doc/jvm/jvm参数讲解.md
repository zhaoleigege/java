# jvm参数讲解

```shell
-XX # 系统级别的配置
非-XX # 应用层面的配置
+ # 表示启用
- # 表示禁用

格式： -XX: <name>=<value> # 有简写格式（去掉等于）
```

```shell
-XX:+PrintGC # 虚拟机启动后遇到GC就打印日志
-XX:+UseSerialGC # 配置串行回收器（可以配置并行回收器）
-XX:+PrintGCDetails # 查看相信信息包括各个区的情况
-Xms: # 设置java程序启动时初始堆的大小
-Xmx: # 设置java程序获得的最大堆大小
# 可以设置堆初始值与最大堆一样，减少GC的次数
# 年轻代和年老带的大小
# 串行和并行回收器
# 内存日志分析+HeapDumpOnOutOfMemoryError
```

查看当前jvm的信息

```shell
java -XX:+PrintCommandLineFlags -version
```

显示结果

    ```shell
-XX:InitialHeapSize=67108864 -XX:MaxHeapSize=1073741824 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
    ```



实例1:

```shell
# 内存泄漏分析
-XX:+HeapDumpOnOutOfMemoryError
# 使用Memory Analyzer分析
```

实例2:

```shell
-Xms5m -Xmx20m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
```

