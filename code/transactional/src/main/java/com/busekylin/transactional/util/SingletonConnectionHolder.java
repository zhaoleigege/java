package com.busekylin.transactional.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SingletonConnectionHolder {
    private static final ThreadLocal<Map<DataSource, Connection>> dataSourceThread = new ThreadLocal<>();

    public static Connection getConnection(DataSource dataSource) throws SQLException {
        Map<DataSource, Connection> connectionMap = dataSourceThread.get();
        if (connectionMap == null) {
            connectionMap = new HashMap<>();
            connectionMap.put(dataSource, dataSource.getConnection());
            dataSourceThread.set(connectionMap);
        }

        return dataSourceThread.get().get(dataSource);
    }

    public static void removeConnection(DataSource dataSource){
        Map<DataSource, Connection> connectionMap = dataSourceThread.get();
        if (connectionMap != null) {
            connectionMap.remove(dataSource);
        }
    }
}
