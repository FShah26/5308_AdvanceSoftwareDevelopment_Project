package com.group9.server.Announcements.Admin;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AnnouncementPersistence implements IAnnouncementPersistence {

    final String MAKE_ANNOUNCEMENT = "{call makeNewAnnouncement(?, ?, ?, ?, ?)}";
    final String GET_COURSES = "{call getAssignedCourses(?)}";
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
            statement.registerOutParameter(5, Types.VARCHAR);
            statement.setString(1, userId);
            statement.setString(2, userRole);
            statement.setString(3, message);
            statement.setString(4, courseId);
            statement.execute();
            output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
        return output;
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(GET_COURSES);
        statement.setString(1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

}
