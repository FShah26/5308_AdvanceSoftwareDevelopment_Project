package com.group9.server.Announcements.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IFetchAnnouncementsFromPersistence {
    ResultSet fetchAnnouncementsFromDatabase() throws SQLException;
}
