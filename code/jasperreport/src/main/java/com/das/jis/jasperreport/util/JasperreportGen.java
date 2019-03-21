package com.das.jis.jasperreport.util;

import net.sf.jasperreports.engine.*;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

public class JasperreportGen {
    public static void main(String[] args) throws JRException, FileNotFoundException {
        /* 读取报表模版 */
        JasperReport jasperReport = JasperCompileManager.compileReport(
                JasperreportGen.class.getClassLoader().getResourceAsStream("jasperreport/person.jrxml")
        );

        JasperPrint print = JasperFillManager.fillReport(
                jasperReport,
                new HashMap() {{
                    put("name", "赵磊");
                    put("age", 21);
                }},
                new JREmptyDataSource()
        );

        /* 生成pdf文件 */
        OutputStream out = new BufferedOutputStream(new FileOutputStream("1.pdf"));
        JasperExportManager.exportReportToPdfStream(print, out);
    }

    static class ObjectBean {
        List<Person> personList;

        public ObjectBean() {

        }

        public ObjectBean(List<Person> personList) {
            this.personList = personList;
        }

        public List<Person> getPersonList() {
            return personList;
        }

        public void setPersonList(List<Person> personList) {
            this.personList = personList;
        }
    }
}
