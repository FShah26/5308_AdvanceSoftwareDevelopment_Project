package com.group9.server.Announcements.Student;

import java.sql.ResultSet;

public interface FetchAnnouncementsPersistence {
    ResultSet fetchAnnouncementsFromDatabase();
}
