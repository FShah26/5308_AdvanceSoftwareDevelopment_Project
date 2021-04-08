package com.group9.server.Database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class SingletonDatabase implements ISingletonDatabase {
    private static SingletonDatabase database = null;
    private static Connection connection = null;

    private SingletonDatabase() {
        //Keep it private and empty to prevent instantiation
    }

    @Override
    public ISingletonDatabase getInstance() {
        if (database == null) {
            database = new SingletonDatabase();
        }
        return database;
    }

    @Override
    public Connection getConnection(DatabaseConfig config) throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(config.url, config.user, config.password);
        }
        return connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
