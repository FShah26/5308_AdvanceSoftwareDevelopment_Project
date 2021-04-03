package com.group9.server.Announcements.Student;

import com.group9.server.Database.ISingletonDatabase;
import com.group9.server.Database.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FetchAnnouncementsImpl implements FetchAnnouncementsFromPersistence {
    Connection connection;

    @Autowired
    public FetchAnnouncementsImpl(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchAnnouncementsFromDatabase() throws SQLException {
        CallableStatement statement = connection.prepareCall("{call fetch_announcements()}");
        return statement.executeQuery();
    }
}
