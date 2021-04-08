package com.group9.server.Announcements.Student;

public class SingleAnnouncement implements ISingleAnnouncement {
    private String userId;
    private String announcement;

    @Override
    public void printAnnouncement() {
        System.out.println(userId);
        System.out.println("  Message: " + announcement + "\n");
    }

    @Override
    public void setUserId(String id) {
        this.userId = id;
    }

    @Override
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }
}
