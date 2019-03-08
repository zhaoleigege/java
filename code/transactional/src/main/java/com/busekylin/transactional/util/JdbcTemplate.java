package com.busekylin.transactional.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean execute(String sql) throws SQLException {
        return getConnection().prepareStatement(sql).execute();
    }

    private Connection getConnection() throws SQLException {
        return SingletonConnectionHolder.getConnection(dataSource);
    }
}
