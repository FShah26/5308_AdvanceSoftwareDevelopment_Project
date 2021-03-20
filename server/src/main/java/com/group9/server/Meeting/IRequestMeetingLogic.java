package com.group9.server.Meeting;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRequestMeetingLogic
{
    RegisteredCourses viewCourses(String studentId) throws SQLException;
    String raiseMeetingRequest(String courseId,String studentId,String reason) throws SQLException;
    ArrayList<MeetingDetails> meetings(String studentId);
}
