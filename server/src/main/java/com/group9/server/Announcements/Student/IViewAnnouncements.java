package com.group9.server.Announcements.Student;

import com.group9.server.IExecuteAction;

public interface IViewAnnouncements extends IExecuteAction {
    IAnnouncementList fetchAnnouncements();

    void displayAllAnnouncements();
}
