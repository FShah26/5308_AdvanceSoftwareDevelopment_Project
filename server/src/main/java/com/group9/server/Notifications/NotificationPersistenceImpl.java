package com.group9.server.Notifications;

import com.group9.server.Database.ISingletonDatabase;
import com.group9.server.Database.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotificationPersistenceImpl implements NotificationPersistence {
    Connection connection;

    @Autowired
    public NotificationPersistenceImpl(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchNotificationsFromDatabase(String user) throws SQLException {
        final String FETCH_USER_NOTIFICATION = "{call fetch_user_notifications(?)}";
        CallableStatement statement = connection.prepareCall(FETCH_USER_NOTIFICATION);
        statement.setString(1, user);
        return statement.executeQuery();
    }
}
