package com.group9.server.Announcements.Admin;

public interface IAnnouncementLogic {
    String makeAnnouncement(String userRole, String courseId, String message, String userId);

    int validateCourseId(String facultyId, String courseId);
}
