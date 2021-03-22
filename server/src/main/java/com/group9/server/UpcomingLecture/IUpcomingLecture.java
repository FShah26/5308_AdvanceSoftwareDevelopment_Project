package com.group9.server.UpcomingLecture;

import java.sql.SQLException;

public interface IUpcomingLecture {
     void lectureDisplay(String username) throws SQLException;
     void checkinput() throws SQLException;
     void selectCourse(int number) throws SQLException;
}
