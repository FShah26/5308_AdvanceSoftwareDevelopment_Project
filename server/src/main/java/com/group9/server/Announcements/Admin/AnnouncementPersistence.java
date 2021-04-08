package com.group9.server.Announcements.Admin;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AnnouncementPersistence implements IAnnouncementPersistence {
    private static final int USER_ID = 1;
    private static final int FACULTY_ID = 1;
    private static final int USER_ROLE = 2;
    private static final int MESSAGE = 3;
    private static final int COURSE_ID = 4;
    private static final int OUTPUT = 5;
    private static final String MESSAGE_RETURN = "message";

    private static final String MAKE_ANNOUNCEMENT = "{call makeNewAnnouncement(?, ?, ?, ?, ?)}";
    private static final String GET_COURSES = "{call getAssignedCourses(?)}";
    Connection connection;

    public AnnouncementPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String InsertAnnouncement(String userRole, String courseId, String message, String userId) {
        String output = "";
        try {
            CallableStatement statement = connection.prepareCall(MAKE_ANNOUNCEMENT);
            statement.registerOutParameter(OUTPUT, Types.VARCHAR);
            statement.setString(USER_ID, userId);
            statement.setString(USER_ROLE, userRole);
            statement.setString(MESSAGE, message);
            statement.setString(COURSE_ID, courseId);
            statement.execute();
            output = statement.getString(MESSAGE_RETURN);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
        return output;
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(GET_COURSES);
        statement.setString(FACULTY_ID, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

}
