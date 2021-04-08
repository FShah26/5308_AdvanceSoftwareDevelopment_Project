package com.group9.server.Announcements.Student;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementList implements IAnnouncementList {
    List<ISingleAnnouncement> announcementList;

    public AnnouncementList() {
        announcementList = new ArrayList<>();
    }

    @Override
    public void printAllAnnouncements() {
        System.out.println("============== Announcements ============== ");
        for (ISingleAnnouncement announcement : announcementList) {
            announcement.printAnnouncement();
        }
    }

    @Override
    public void addAnnouncement(ISingleAnnouncement announcement) {
        announcementList.add(announcement);
    }
}
