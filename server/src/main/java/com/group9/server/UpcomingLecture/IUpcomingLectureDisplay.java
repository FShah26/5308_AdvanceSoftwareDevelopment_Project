package com.group9.server.UpcomingLecture;

import java.sql.SQLException;

public interface IUpcomingLectureDisplay {
     void lectureDisplay(String username) throws SQLException;
     void checkInput() throws SQLException;
     void selectCourse(int number) throws SQLException;
}