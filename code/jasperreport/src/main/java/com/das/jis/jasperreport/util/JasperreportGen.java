package com.das.jis.jasperreport.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.stream.Stream;

public class JasperreportGen {
    public static void main(String[] args) throws JRException, FileNotFoundException {
        /* 读取报表模版 */
        JasperReport jasperReport = JasperCompileManager.compileReport(
                JasperreportGen.class.getClassLoader().getResourceAsStream("jasperreport/person.jrxml")
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
    }
}
