# java agent的实现

java agent可以在main函数执行之前先执行，但是需要java agent和main函数的代码是被同一个累加器加载。

实现步骤

1. 在java类中实现`public static void premain(String args, Instrumentation inst)`方法

   ```java
   public static void premain(String args, Instrumentation inst){
       ....    
   }
   ```

2. 在`resources`目录中创建`META-INF/MANIFEST.MF`文件

   ```txt
   Manifest-Version: 1.0
   Premain-Class: com.busekylin.agent.agent.JVMAgent
   Can-Redefine-Classes: true
   ```

3. `pom.xml`文件添加依赖

   ```xml
   <build>
   	<plugins> 
   		<plugin>
   			<groupId>org.apache.maven.plugins</groupId>
   			<artifactId>maven-assembly-plugin</artifactId>
   			<version>2.6</version>
   			<configuration>
   				<descriptorRefs>
   					<descriptorRef>jar-with-dependencies</descriptorRef>
   				</descriptorRefs>
   				<archive>
   					<manifestFile>src/main/resources/META-INF/MANIFEST.MF</manifestFile>
   				</archive>
   			</configuration>
   			<executions>
   				<execution>
   					<id>assemble-all</id>
   					<phase>package</phase>
   					<goals>
   						<goal>single</goal>
   					</goals>
   				</execution>
   			</executions>
   		</plugin>
   	</plugins>
     <resources>
   		<resource>
   			<directory>${basedir}/src/main/resources</directory>
   		</resource>
   		<resource>
   			<directory>${basedir}/src/main/java</directory>
   		</resource>
   	</resources>
   </build>
   ```

4. 执行命令生成`jar`包

   ```shell
   mvn clean package
   ```

5. 调用该方法

   ```shell
   java -javaagent:target/javaagent-1.0-jar-with-dependencies.jar[=agentArgs] -classpath target/javaagent-1.0-jar-with-dependencies.jar com.das.his.javaagent.agent.AgentTestMain mainArgs1 mainArgs2
   ```

   比如

   ```shell
   java -javaagent:target/javaagent-1.0-jar-with-dependencies.jar=12 -classpath target/javaagent-1.0-jar-with-dependencies.jar com.das.his.javaagent.agent.AgentTestMain 1 2
   ```

   ```shell
   java -javaagent:target/agent-1.0.RELEASE-jar-with-dependencies.jar -classpath target/agent-1.0.RELEASE-jar-with-dependencies.jar com.busekylin.agent.agent.AgentTestMain
   ```



#### 参考资料

* [Java字节码Agent简单上手](https://blog.csdn.net/f59130/article/details/78367045)    **强烈推荐**