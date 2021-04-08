package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class RequestMeetingPersistence implements IRequestMeetingPersistence {
    private static final int STUDENT_ID = 1;
    private static final int COURSE_ID = 2;
    private static final int REASON = 3;
    private static final int OUTPUT = 4;
    private static final String MESSAGE = "message";
    private static final String REGISTERED_COURSE = "{call registeredCourse(?)}";
    private static final String REQUEST_MEETING = "{call requestMeetingStudent(?,?,?,?)}";
    private static final String VIEW_MEETING = "{call viewMeetingStatuses(?)}";
    Connection connection;

    public RequestMeetingPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        database = database.getInstance();
        connection = database.getConnection(config);
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(REGISTERED_COURSE);
        statement.setString(STUDENT_ID, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String setMeeting(String courseId, String studentId, String reason) throws SQLException {
        CallableStatement statement = connection.prepareCall(REQUEST_MEETING);

        statement.registerOutParameter(OUTPUT, Types.VARCHAR);
        statement.setString(STUDENT_ID, studentId);
        statement.setString(COURSE_ID, courseId);
        statement.setString(REASON, reason);
        statement.execute();
        String output = statement.getString(MESSAGE);
        return output;
    }

    @Override
    public ResultSet viewMeeting(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(VIEW_MEETING);
        statement.setString(STUDENT_ID, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
