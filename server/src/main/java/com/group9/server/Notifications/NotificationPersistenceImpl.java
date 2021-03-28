package com.group9.server.Notifications;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class NotificationPersistenceImpl implements NotificationPersistence {
    Connection con;

    @Autowired
    public NotificationPersistenceImpl(DBConfig config) throws SQLException {
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchNotificationsFromDatabase(String user) throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_user_notifications(?)}");
        statement.setString(1, user);
        return statement.executeQuery();
    }
}
