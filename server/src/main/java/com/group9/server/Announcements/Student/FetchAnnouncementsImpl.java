package com.group9.server.Announcements.Student;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class FetchAnnouncementsImpl implements FetchAnnouncementsFromPersistence {
    Connection con;

    @Autowired
    public FetchAnnouncementsImpl(DBConfig config) throws SQLException {
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchAnnouncementsFromDatabase() throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_announcements()}");
        return statement.executeQuery();
    }
}
