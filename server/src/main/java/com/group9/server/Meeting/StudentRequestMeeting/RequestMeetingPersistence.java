package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class RequestMeetingPersistence implements IRequestMeetingPersistence {
    private static final int REQUEST_MEETING_PARAMETER_INDEX_1 = 1;
    private static final int REQUEST_MEETING_PARAMETER_INDEX_2 = 2;
    private static final int REQUEST_MEETING_PARAMETER_INDEX_3 = 3;
    private static final int REQUEST_MEETING_PARAMETER_INDEX_4 = 4;
    private static final String OUT_PARAMETER = "message";
    private static final String REGISTERED_COURSE = "{call registeredCourse(?)}";
    private static final String REQUEST_MEETING = "{call requestMeetingStudent(?,?,?,?)}";
    private static final String VIEW_MEETING = "{call viewMeetingStatuses(?)}";
    Connection connection;

    public RequestMeetingPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        database = database.getInstance();
        connection = database.getConnection(config);
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(REGISTERED_COURSE);
        statement.setString(REQUEST_MEETING_PARAMETER_INDEX_1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String setMeeting(String courseId, String studentId, String reason) throws SQLException {
        CallableStatement statement = connection.prepareCall(REQUEST_MEETING);

        statement.registerOutParameter(REQUEST_MEETING_PARAMETER_INDEX_4, Types.VARCHAR);
        statement.setString(REQUEST_MEETING_PARAMETER_INDEX_1, studentId);
        statement.setString(REQUEST_MEETING_PARAMETER_INDEX_2, courseId);
        statement.setString(REQUEST_MEETING_PARAMETER_INDEX_3, reason);
        statement.execute();
        String output = statement.getString(OUT_PARAMETER);
        return output;
    }

    @Override
    public ResultSet viewMeeting(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(VIEW_MEETING);
        statement.setString(REQUEST_MEETING_PARAMETER_INDEX_1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
