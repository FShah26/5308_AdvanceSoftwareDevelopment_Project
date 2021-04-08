package com.group9.server.Notes;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class NotesPersistence implements INotesPersistence {
    private static final String FETCH_NOTES = "{call fetchNotes(?, ?)}";
    private static final String ADD_NOTES = "{call addNotes(?, ?, ?, ?)}";
    private static final int STUDENT_ID = 1;
    private static final int COURSE_ID = 2;
    private static final int NOTES = 3;
    private static final String RETURN_MESSAGE = "message";
    Connection connection;

    public NotesPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchNotes(String studentId, String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_NOTES);
        statement.setString(STUDENT_ID, studentId);
        statement.setString(COURSE_ID, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertNotes(String studentId, String courseId, String notes) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_NOTES);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setString(STUDENT_ID, studentId);
        statement.setString(COURSE_ID, courseId);
        statement.setString(NOTES, notes);
        statement.execute();

        return statement.getString(RETURN_MESSAGE);
    }
}
