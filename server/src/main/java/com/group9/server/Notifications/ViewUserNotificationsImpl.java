package com.group9.server.Notifications;

import org.springframework.stereotype.Component;

@Component
public class ViewUserNotificationsImpl implements ViewUserNotifications {
    NotificationPersistence persistence;

    public ViewUserNotificationsImpl(NotificationPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public void displayAllNotifications(String user) {

    }
}
