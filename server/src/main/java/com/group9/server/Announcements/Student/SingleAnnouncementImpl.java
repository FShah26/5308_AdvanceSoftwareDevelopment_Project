package com.group9.server.Announcements.Student;

public class SingleAnnouncementImpl implements SingleAnnouncement {
    private String userID;
    private String announcement;

    @Override
    public void printAnnouncement() {
        System.out.println(userID);
        System.out.println("  Message: " + announcement + "\n");
    }

    @Override
    public void setUserID(String id) {
        this.userID = id;
    }

    @Override
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
