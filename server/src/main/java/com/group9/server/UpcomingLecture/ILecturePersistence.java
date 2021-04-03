package com.group9.server.UpcomingLecture;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ILecturePersistence {
    ResultSet viewLecture(String courseId) throws SQLException;
}
