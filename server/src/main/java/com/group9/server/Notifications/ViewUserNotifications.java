package com.group9.server.Notifications;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewUserNotifications implements IViewUserNotifications {
    INotificationPersistence persistence;
    private static final int FROM = 1;
    private static final int MESSAGE = 2;

    public ViewUserNotifications(INotificationPersistence persistence) {
        this.persistence = persistence;
    }

    private void printNotification(String from, String message) {
        System.out.println("From:" + from + "\nMessage:" + message + "\n");
    }

    @Override
    public void displayAllNotifications(String user) {
        try {
            ResultSet notificationsSet = persistence.fetchNotificationsFromDatabase(user);

            System.out.println("============== Notifications ============== ");
            while (notificationsSet.next()) {
                String from = notificationsSet.getString(FROM);
                String message = notificationsSet.getString(MESSAGE);
                printNotification(from, message);
            }

        } catch (SQLException exception) {
            System.out.println("Fetching notifications failed");
            exception.printStackTrace();
        }
    }

    @Override
    public void execute(String userRole, String userId) {
        displayAllNotifications(userId);
    }
}
