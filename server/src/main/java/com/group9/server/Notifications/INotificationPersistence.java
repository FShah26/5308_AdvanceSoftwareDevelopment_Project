package com.group9.server.Notifications;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface INotificationPersistence {
    ResultSet fetchNotificationsFromDatabase(String user) throws SQLException;
}
