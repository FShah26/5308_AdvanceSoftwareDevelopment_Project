package com.group9.server.Announcements;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AnnouncementPersistence implements IAnnouncementPersistence {
    @Autowired
    DBConfig db;
    @Override
    public String InsertAnnouncement(String user_role, String message) {
        String dbURL = db.url;
        String user = db.user;
        String password = db.password;
        String output="";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call Make_NewAnnouncement(?, ?, ?, ?)}");
        ) {

            statement.registerOutParameter(4, Types.VARCHAR);
            statement.setString(1, "");
            statement.setString(2, user_role);
            statement.setString(3, message);
            statement.execute();
             output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output ="Error Catched";
        }
        return output;
    }
}
