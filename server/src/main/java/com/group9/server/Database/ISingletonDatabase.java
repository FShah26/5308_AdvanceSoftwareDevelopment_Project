package com.group9.server.Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISingletonDatabase {
    ISingletonDatabase getInstance();

    Connection getConnection(DBConfig config) throws SQLException;

    void closeConnection() throws SQLException;
}
