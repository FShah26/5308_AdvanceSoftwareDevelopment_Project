package com.group9.server.Announcements.Admin;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AnnouncementPersistence implements IAnnouncementPersistence {
    private static final int ANNOUNCEMENT_PARAMETER_INDEX_1 = 1;
    private static final int ANNOUNCEMENT_PARAMETER_INDEX_2 = 2;
    private static final int ANNOUNCEMENT_PARAMETER_INDEX_3 = 3;
    private static final int ANNOUNCEMENT_PARAMETER_INDEX_4 = 4;
    private static final int ANNOUNCEMENT_PARAMETER_INDEX_5 = 5;
    private static final String OUT_PARAMETER = "message";
    private static final String ERROR_OUTPUT = "Error Catched";
    private static final String MAKE_ANNOUNCEMENT = "{call makeNewAnnouncement(?, ?, ?, ?, ?)}";
    private static final String GET_COURSES = "{call getAssignedCourses(?)}";
    Connection connection;

    public AnnouncementPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String InsertAnnouncement(String userRole, String courseId, String message, String userId) {
        String output;
        try {
            CallableStatement statement = connection.prepareCall(MAKE_ANNOUNCEMENT);
            statement.registerOutParameter(ANNOUNCEMENT_PARAMETER_INDEX_5, Types.VARCHAR);
            statement.setString(ANNOUNCEMENT_PARAMETER_INDEX_1, userId);
            statement.setString(ANNOUNCEMENT_PARAMETER_INDEX_2, userRole);
            statement.setString(ANNOUNCEMENT_PARAMETER_INDEX_3, message);
            statement.setString(ANNOUNCEMENT_PARAMETER_INDEX_4, courseId);
            statement.execute();
            output = statement.getString(OUT_PARAMETER);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = ERROR_OUTPUT;
        }
        return output;
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(GET_COURSES);
        statement.setString(ANNOUNCEMENT_PARAMETER_INDEX_1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

}
