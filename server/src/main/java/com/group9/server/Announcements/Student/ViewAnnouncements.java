package com.group9.server.Announcements.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewAnnouncements implements IViewAnnouncements {
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
                announcement.setUserID(announcementResultSet.getString(1));
                announcement.setAnnouncement(announcementResultSet.getString(2));
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
