package com.group9.server.Announcements.Student;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FetchAnnouncementsPersistence implements IFetchAnnouncementsFromPersistence {
    private static final String FETCH_ANNOUNCEMENT = "{call fetchAnnouncements()}";
    Connection connection;

    public FetchAnnouncementsPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchAnnouncementsFromDatabase() throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_ANNOUNCEMENT);
        return statement.executeQuery();
    }
}
