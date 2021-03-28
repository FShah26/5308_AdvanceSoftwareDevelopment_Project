package com.group9.server.Notifications;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotificationPersistenceImpl implements NotificationPersistence {
    Connection con;

    @Autowired
    public NotificationPersistenceImpl(DBConfig config) throws SQLException {
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchNotificationsFromDatabase() throws SQLException {
        return null;
    }
}
