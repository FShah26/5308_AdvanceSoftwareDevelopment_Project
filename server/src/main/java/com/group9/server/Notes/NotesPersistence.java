package com.group9.server.Notes;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class NotesPersistence implements INotesPersistence {
    final String FETCH_NOTES = "{call fetchNotes(?, ?)}";
    final String ADD_NOTES = "{call addNotes(?, ?, ?, ?)}";
    Connection connection;

    public NotesPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchNotes(String studentId, String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_NOTES);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertNotes(String studentId, String courseId, String notes) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_NOTES);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        statement.setString(3, notes);
        statement.execute();

        return statement.getString("message");
    }
}
