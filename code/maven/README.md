# maven项目demo
这是一个简单的`vert.x`maven项目

运行项目
```shell
mvn compile exec:java
```

打成jar包
```shell
mvn package
```
执行jar包
```shell
java -jar target/maven-1.0-fat.jar
```

直接运行生成jar包然后运行
```shell
mvn package exec:java
```

访问http://localhost:8080

#### 参考资料
* [使用IDEA 创建一个vert.x 项目](https://blog.csdn.net/u010355769/article/details/79428700)
* [Vert.x Simplest Maven project](https://github.com/vert-x3/vertx-examples/tree/master/maven-simplest)