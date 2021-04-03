package com.group9.server.Announcements.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IAnnouncementPersistence {
    String InsertAnnouncement(String user, String courseId, String message, String userId);

    ResultSet getFacultyCourses(String facultyId) throws SQLException;
}
