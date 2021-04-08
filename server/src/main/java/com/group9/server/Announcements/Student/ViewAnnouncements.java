package com.group9.server.Announcements.Student;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewAnnouncements implements IViewAnnouncements {
    private static final int USER_ID = 1;
    private static final int ANNOUNCEMENT = 2;
    IAnnouncementList IAnnouncementList;
    IFetchAnnouncementsFromPersistence announcementsPersistence;
    ResultSet announcementResultSet;

    public ViewAnnouncements(IFetchAnnouncementsFromPersistence persistence) {
        announcementsPersistence = persistence;
        IAnnouncementList = new AnnouncementList();
    }

    @Override
    public IAnnouncementList fetchAnnouncements() {
        try {
            announcementResultSet = announcementsPersistence.fetchAnnouncementsFromDatabase();
        } catch (SQLException e) {
            System.out.println("Fetching announcements failed");
            e.printStackTrace();
        }
        return IAnnouncementList;
    }

    @Override
    public void displayAllAnnouncements() {
        fetchAnnouncements();
        try {
            while (announcementResultSet.next()) {
                ISingleAnnouncement announcement = new SingleAnnouncement();
                announcement.setUserId(announcementResultSet.getString(USER_ID));
                announcement.setAnnouncement(announcementResultSet.getString(ANNOUNCEMENT));
                IAnnouncementList.addAnnouncement(announcement);
            }

            printAnnouncementList();
        } catch (SQLException e) {
            System.out.println("Displaying announcements failed");
            e.printStackTrace();
        }
    }

    public void printAnnouncementList() {
        IAnnouncementList.printAllAnnouncements();
    }

    @Override
    public void execute(String userRole, String userId) {
        displayAllAnnouncements();
    }
}
