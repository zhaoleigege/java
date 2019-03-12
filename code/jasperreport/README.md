# Jasperreport报表制作



1. 下载[jaspersoft studio报表设计工具](https://community.jaspersoft.com/project/jaspersoft-studio/releases)。

2. 打开`jaspersoft studio`创建一个新的`Jasper Report`，可以开始进行拖拽画报表，注意报表需要使用显示中文时，要设置`华体宋文`、`微软雅黑`等字体。

3. 拷贝生成的`xxx.jrxml`文件到项目中

4. 导入需要的依赖包

   ```xml
   <dependency>
   	<groupId>net.sf.jasperreports</groupId>
   	<artifactId>jasperreports</artifactId>
   	<version>6.7.1</version>
   </dependency>
   ```

5. 设置中文字体展示

   1. 在在`src/main/resources`目录下添加文件两个字体文件

      * fonts/fonts.xml
      * fonts/STSONG.tff

   2. 在`src/main/resources`目录下添加配置文件`jasperreports_extension.properties`

      ```properties
      net.sf.jasperreports.extension.registry.factory.simple.font.families=net.sf.jasperreports.engine.fonts.SimpleFontExtensionsRegistryFactory
      net.sf.jasperreports.extension.simple.font.families.lobstertwo=fonts/fonts.xml
      ```

6. 读取报表模版并填充数据生成pdf文件

   ```java
   /* 读取报表模版 */
   JasperReport jasperReport = JasperCompileManager.compileReport(            JasperreportGen.class.getClassLoader().getResourceAsStream("jasperreport/person.jrxml")
           );
   
   JasperPrint print = JasperFillManager.fillReport(
   	jasperReport,
   	new HashMap<>(),
   	/* 获取数据源 */
   	new JRBeanArrayDataSource(
   			Stream.generate(() -> new Person("中文", 25)).limit(5).toArray()
   		)
   	);
   
   /* 生成pdf文件 */
   OutputStream out = new BufferedOutputStream(new FileOutputStream("1.pdf"));
   JasperExportManager.exportReportToPdfStream(print, out);
   ```

   