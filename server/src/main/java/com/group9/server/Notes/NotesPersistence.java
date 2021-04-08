package com.group9.server.Notes;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class NotesPersistence implements INotesPersistence {
    private static final String FETCH_NOTES = "{call fetchNotes(?, ?)}";
    private static final String ADD_NOTES = "{call addNotes(?, ?, ?, ?)}";
    private static final int NOTES_PARAMETER_INDEX_1 = 1;
    private static final int NOTES_PARAMETER_INDEX_2 = 2;
    private static final int NOTES_PARAMETER_INDEX_3 = 3;
    private static final String OUT_PARAMETER = "message";
    Connection connection;

    public NotesPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchNotes(String studentId, String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_NOTES);
        statement.setString(NOTES_PARAMETER_INDEX_1, studentId);
        statement.setString(NOTES_PARAMETER_INDEX_2, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertNotes(String studentId, String courseId, String notes) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_NOTES);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setString(NOTES_PARAMETER_INDEX_1, studentId);
        statement.setString(NOTES_PARAMETER_INDEX_2, courseId);
        statement.setString(NOTES_PARAMETER_INDEX_3, notes);
        statement.execute();

        return statement.getString(OUT_PARAMETER);
    }
}
