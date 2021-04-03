package com.group9.server.Announcements.Admin;

import com.group9.server.IExecuteAction;

public interface IAnnouncementInput extends IExecuteAction {

    void announcement(String userRole, String userId);

    void selectOption();
}
