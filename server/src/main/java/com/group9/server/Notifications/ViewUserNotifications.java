package com.group9.server.Notifications;

import com.group9.server.IExecuteAction;

public interface ViewUserNotifications extends IExecuteAction {
    void displayAllNotifications(String user);
}
