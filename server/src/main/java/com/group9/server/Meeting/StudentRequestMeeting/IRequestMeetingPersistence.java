package com.group9.server.Meeting.StudentRequestMeeting;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRequestMeetingPersistence {
    ResultSet fetchRegisteredCourses(String studentID) throws SQLException;
    String setMeeting(String courseId,String studentId,String reason) throws SQLException;
    ResultSet viewMeeting(String studentId) throws SQLException;
}

