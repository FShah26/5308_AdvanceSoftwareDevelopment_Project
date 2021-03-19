package com.group9.server.Announcements.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewAnnouncementsImpl implements ViewAnnouncements {
    AnnouncementList announcementList;
    FetchAnnouncementsFromPersistence announcementsPersistence;
    ResultSet announcementResultSet;

    @Autowired
    public ViewAnnouncementsImpl(FetchAnnouncementsFromPersistence persistence) {
        announcementsPersistence = persistence;
        announcementList = new AnnouncementListImpl();
    }

    @Override
    public AnnouncementList fetchAnnouncements() {
        try {
            announcementResultSet = announcementsPersistence.fetchAnnouncementsFromDatabase();
        } catch (SQLException e) {
            System.out.println("Fetching announcements failed");
            e.printStackTrace();
        }
        return announcementList;
    }

    @Override
    public void displayAllAnnouncements() {
        fetchAnnouncements();
        try {
            while (announcementResultSet.next()) {
                SingleAnnouncement announcement = new SingleAnnouncementImpl();
                announcement.setUserID(announcementResultSet.getString(1));
                announcement.setAnnouncement(announcementResultSet.getString(2));
                announcementList.addAnnouncement(announcement);
            }

            printAnnouncementList();
        } catch (SQLException e) {
            System.out.println("Displaying announcements failed");
            e.printStackTrace();
        }
    }

    public void printAnnouncementList() {
        announcementList.printAllAnnouncements();
    }
}
