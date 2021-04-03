package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class RequestMeetingPersistence implements IRequestMeetingPersistence {
    final String REGISTERED_COURSE = "{call RegisteredCourses(?)}";
    final String REQUEST_MEETING = "{call RequestMeeting_Student(?,?,?,?)}";
    final String VIEW_MEETING = "{call ViewMeetingStatus(?)}";
    Connection connection;

    public RequestMeetingPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        database = database.getInstance();
        connection = database.getConnection(config);
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(REGISTERED_COURSE);
        statement.setString(1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String setMeeting(String courseId, String studentId, String reason) throws SQLException {
        CallableStatement statement = connection.prepareCall(REQUEST_MEETING);

        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        statement.setString(3, reason);
        statement.execute();
        String output = statement.getString("message");
        return output;
    }

    @Override
    public ResultSet viewMeeting(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(VIEW_MEETING);
        statement.setString(1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
