package com.group9.server.Announcements.Student;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementListImpl implements AnnouncementList {
    List<SingleAnnouncement> announcementList;

    public AnnouncementListImpl() {
        announcementList = new ArrayList<>();
    }

    @Override
    public void printAllAnnouncements() {
        System.out.println("User          Announcement");
        for(SingleAnnouncement announcement: announcementList){
            announcement.printAnnouncement();
        }
    }

    @Override
    public void addAnnouncement(SingleAnnouncement announcement) {
        announcementList.add(announcement);
    }
}
