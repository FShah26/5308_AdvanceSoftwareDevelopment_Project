package com.group9.server.UpcomingLecture;

import com.group9.server.Database.DBConfig;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class LecturePersistence implements ILecturePersistence {
    DBConfig config;
    Connection con;

    public LecturePersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }
    @Override
    public ResultSet viewLecture(String courseid) throws SQLException {
        CallableStatement statement = con.prepareCall("{call UpcomingLectures(?)}");
        statement.setString(1, courseid);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
