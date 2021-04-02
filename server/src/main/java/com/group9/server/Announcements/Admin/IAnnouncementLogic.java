package com.group9.server.Announcements.Admin;

public interface IAnnouncementLogic {
    String make_announcement(String userRole,String courseId, String message, String userId);
    int validateCourseId(String facultyId, String courseId);
}
