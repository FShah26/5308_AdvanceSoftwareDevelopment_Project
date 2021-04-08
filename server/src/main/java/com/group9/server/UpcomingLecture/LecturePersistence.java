package com.group9.server.UpcomingLecture;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LecturePersistence implements ILecturePersistence {
    final String UPCOMING_LECTURE = "{call upcomingLecture(?)}";
    final int UPCOMING_LECTURE_PROC_ARG = 1;
    Connection connection;

    public LecturePersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet viewLecture(String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(UPCOMING_LECTURE);
        statement.setString(UPCOMING_LECTURE_PROC_ARG, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
