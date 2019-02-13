# 配置maven私服

1. maven私服选择[artifactory](https://www.jfrog.com/confluence/)

   Artifactory支持多种语言的包依赖管理，这里我们把它当作java的maven包依赖管理

2. 安装Artifactory

   服务器上先安装好docker环境，然后下载Artifactory的docker文件

   ```shell
   docker pull docker.bintray.io/jfrog/artifactory-oss:latest
   ```

3. 启动Artifactory

   ```shell
   docker run --name artifactory-oss -d -p 8081:8081 docker.bintray.io/jfrog/artifactory-oss:latest
   ```

   <span style="color: red;">**使用外置存储空间遇到了一些问题**</span>

4. 打开网页后选择maven即可配置好maven的镜像库



#### 参考资料

* [使用 Artifactory 搭建 Maven 私服](https://www.jianshu.com/p/dfd02fa239e2)