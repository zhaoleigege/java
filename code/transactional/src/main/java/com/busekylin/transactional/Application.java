package com.busekylin.transactional;

import com.busekylin.transactional.domain.People;
import com.busekylin.transactional.repository.PeopleRepository;
import com.busekylin.transactional.repository.impl.DefaultPeopleRepositoryImpl;
import com.busekylin.transactional.util.JdbcTemplate;
import com.busekylin.transactional.util.TransactionInvocationHandler;
import com.busekylin.transactional.util.TransactionManager;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Properties;

public class Application {
    private static String DB_URL;
    private static String USERNAME;
    private static String PASSWORD;

    /* 从配置文件中读取数据库连接相关的信息 */
    static {
        try {
            InputStream input = Application.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(input);

            Application.DB_URL = properties.getProperty("dbUrl");
            Application.USERNAME = properties.getProperty("username");
            Application.PASSWORD = properties.getProperty("password");

            Class.forName(properties.getProperty("className"));
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL(Application.DB_URL);
        dataSource.setUser(Application.USERNAME);
        dataSource.setPassword(Application.PASSWORD);

        PeopleRepository repository = (PeopleRepository) Proxy.newProxyInstance(
                Application.class.getClassLoader(),
                new Class[]{PeopleRepository.class},
                new TransactionInvocationHandler(new DefaultPeopleRepositoryImpl(
                        new JdbcTemplate(dataSource)
                ), new TransactionManager(dataSource))
        );

//        PeopleRepository repository = new DefaultPeopleRepositoryImpl(new JdbcTemplate(dataSource));
        repository.addPeople(new People("赵磊", 26));
    }
}
