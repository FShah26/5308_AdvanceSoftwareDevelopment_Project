package com.group9.server.Notifications;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NotificationPersistence implements INotificationPersistence {
    private static final String FETCH_USER_NOTIFICATION = "{call fetchUserNotifications(?)}";
    private static final int NOTIFICATION_PARAMETER_INDEX_1 = 1;
    Connection connection;

    public NotificationPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchNotificationsFromDatabase(String user) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_USER_NOTIFICATION);
        statement.setString(NOTIFICATION_PARAMETER_INDEX_1, user);
        return statement.executeQuery();
    }
}
