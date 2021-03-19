package com.group9.server.Announcements;

import java.sql.ResultSet;

public interface FetchAnnouncementsPersistence {
    ResultSet fetchAnnouncementsFromDatabase();
}
