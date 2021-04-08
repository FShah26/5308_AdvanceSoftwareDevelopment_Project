package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class ManageMeetingPersistence implements IManageMeetingPersistence {

    private static final int MANAGE_MEETING_PARAMETER_INDEX_1 = 1;
    private static final int MANAGE_MEETING_PARAMETER_INDEX_2 = 2;
    private static final int MANAGE_MEETING_PARAMETER_INDEX_3 = 3;
    private static final int MANAGE_MEETING_PARAMETER_INDEX_4 = 4;
    private static final String OUT_PARAMETER = "message";

    private static final String SHOW_MEETING = "{call showFacultyMeeting(?,?)}";
    private static final String MEETING_RESPONSE = "{call meetingResponse(?,?,?,?)}";
    Connection connection;

    public ManageMeetingPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet meetingLog(String facultyId, String facultySelection) throws SQLException {
        CallableStatement statement = connection.prepareCall(SHOW_MEETING);
        statement.setString(MANAGE_MEETING_PARAMETER_INDEX_1, facultySelection);
        statement.setString(MANAGE_MEETING_PARAMETER_INDEX_2, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String responseMeeting(int meetingId, String decision, String response) throws SQLException {
        CallableStatement statement = connection.prepareCall(MEETING_RESPONSE);
        statement.registerOutParameter(MANAGE_MEETING_PARAMETER_INDEX_4, Types.VARCHAR);
        statement.setInt(MANAGE_MEETING_PARAMETER_INDEX_1, meetingId);
        statement.setString(MANAGE_MEETING_PARAMETER_INDEX_2, decision);
        statement.setString(MANAGE_MEETING_PARAMETER_INDEX_3, response);
        statement.execute();
        String output = statement.getString(OUT_PARAMETER);
        return output;
    }
}
