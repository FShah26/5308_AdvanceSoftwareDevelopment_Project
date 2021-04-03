package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ManageMeetingPersistence implements IManageMeetingPersistence {
    final String SHOW_MEETING = "{call showfacultymeetings(?,?)}";
    final String MEETING_RESPONSE = "{call meeting_response(?,?,?,?)}";
    Connection connection;

    public ManageMeetingPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet meetingLog(String facultyId, String facultySelection) throws SQLException {
        CallableStatement statement = connection.prepareCall(SHOW_MEETING);
        statement.setString(1, facultySelection);
        statement.setString(2, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String responseMeeting(int meetingId, String decision, String response) throws SQLException {
        CallableStatement statement = connection.prepareCall(MEETING_RESPONSE);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setInt(1, meetingId);
        statement.setString(2, decision);
        statement.setString(3, response);
        statement.execute();
        String output = statement.getString("msg");
        return output;
    }
}
