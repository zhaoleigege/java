package com.busekylin.transactional.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public final void start() throws SQLException {
        getConnection().setAutoCommit(false);
    }

    public final void commit() throws SQLException {
        getConnection().commit();
    }

    public final void rollback() {
        try {
            getConnection().rollback();
        } catch (SQLException e) {
            throw new RuntimeException("connection回滚失败", e);
        }
    }

    public final void close() {
        try {
            getConnection().setAutoCommit(true);
            getConnection().setReadOnly(true);
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException("connection关闭失败", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return SingletonConnectionHolder.getConnection(this.dataSource);
    }
}
