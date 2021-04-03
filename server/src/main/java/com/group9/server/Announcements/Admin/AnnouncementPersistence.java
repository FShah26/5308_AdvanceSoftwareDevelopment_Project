package com.group9.server.Announcements.Admin;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AnnouncementPersistence implements IAnnouncementPersistence {
    @Autowired
    DBConfig db;
    @Override
    public String InsertAnnouncement(String userRole, String courseId,String message,String userId) {
        String dbURL = db.url;
        String user = db.user;
        String password = db.password;
        String output="";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call Make_NewAnnouncement(?, ?, ?, ?, ?)}");
        ) {

            statement.registerOutParameter(5, Types.VARCHAR);
            statement.setString(1, userId);
            statement.setString(2, userRole);
            statement.setString(3, message);
            statement.setString(4,courseId);
            statement.execute();
             output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output ="Error Catched";
        }
        return output;
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException{
        String dbURL = db.url;
        String user = db.user;
        String password = db.password;
        String output="";
        ResultSet set = null;
        Connection conn = DriverManager.getConnection(dbURL, user, password);
        CallableStatement statement = conn.prepareCall("{call get_assigned_courses(?)}");
        statement.setString(1, facultyId);
        set = statement.executeQuery();
        return set;
    }

}
