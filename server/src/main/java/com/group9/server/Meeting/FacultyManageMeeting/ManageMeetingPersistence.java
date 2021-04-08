package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ManageMeetingPersistence implements IManageMeetingPersistence {
    private static final int FACULTY_SELETION = 1;
    private static final int FACULTY_ID = 2;
    private static final int MEETING_ID = 1;
    private static final int DECISION_MADE = 2;
    private static final int RESPONSE_TEXT = 3;
    private static final int OUTPUT = 4;
    private static final String SHOW_MEETING = "{call showFacultyMeeting(?,?)}";
    private static final String MEETING_RESPONSE = "{call meetingResponse(?,?,?,?)}";
    Connection connection;

    public ManageMeetingPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet meetingLog(String facultyId, String facultySelection) throws SQLException {
        CallableStatement statement = connection.prepareCall(SHOW_MEETING);
        statement.setString(FACULTY_SELETION, facultySelection);
        statement.setString(FACULTY_ID, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String responseMeeting(int meetingId, String decision, String response) throws SQLException {
        CallableStatement statement = connection.prepareCall(MEETING_RESPONSE);
        statement.registerOutParameter(OUTPUT, Types.VARCHAR);
        statement.setInt(MEETING_ID, meetingId);
        statement.setString(DECISION_MADE, decision);
        statement.setString(RESPONSE_TEXT, response);
        statement.execute();
        String output = statement.getString("message");
        return output;
    }
}
