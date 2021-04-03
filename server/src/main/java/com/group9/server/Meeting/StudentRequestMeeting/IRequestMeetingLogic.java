package com.group9.server.Meeting.StudentRequestMeeting;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRequestMeetingLogic {
    RegisteredCourses viewCourse(String studentId) throws SQLException;

    String raiseMeetingRequest(String courseId, String studentId, String reason) throws SQLException;

    ArrayList<MeetingDetails> meeting(String studentId);
}
