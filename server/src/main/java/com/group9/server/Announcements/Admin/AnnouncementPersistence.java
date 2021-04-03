package com.group9.server.Announcements.Admin;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AnnouncementPersistence implements IAnnouncementPersistence {

    Connection connection;

    public AnnouncementPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String InsertAnnouncement(String userRole, String courseId, String message, String userId) {
        String output = "";
        try (
                CallableStatement statement = connection.prepareCall("{call Make_NewAnnouncement(?, ?, ?, ?, ?)}")
        ) {

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
        String output = "";
        ResultSet set = null;
        CallableStatement statement = connection.prepareCall("{call get_assigned_courses(?)}");
        statement.setString(1, facultyId);
        set = statement.executeQuery();
        return set;
    }

}
