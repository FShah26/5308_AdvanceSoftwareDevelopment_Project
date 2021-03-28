package com.group9.server.Notifications;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface NotificationPersistence {
    ResultSet fetchNotificationsFromDatabase() throws SQLException;
}
