package com.group9.server.Meeting.Faculty;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IManageMeetingPersistence {
    ResultSet meetingLog(String facultyId,String facultySelection)  throws SQLException;
    String responseMeeting(int meetingId,String decision, String response) throws SQLException;
}
