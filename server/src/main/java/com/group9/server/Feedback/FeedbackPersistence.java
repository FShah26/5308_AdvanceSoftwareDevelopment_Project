package com.group9.server.Feedback;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class FeedbackPersistence implements IFeedbackPersistence {

    private static final int USER_ID = 1;
    private static final int USER_NAME = 2;
    private static final int FEEDBACK = 3;
    private static final int OUTPUT = 5;
    private static final int FETCH_FEEDBACK_FACULTY_ID = 1;
    private static final String OUTPUT_MESSAGE = "message";
    private static final String FETCH_FEEDBACK = "{call fetchFeedback(?)}";
    private static final String ADD_FEEDBACK = "{call addFeedback(?, ?, ?, ?, ?)}";
    private static final int FACULTY_ID = 4;

    Connection connection;

    public FeedbackPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchFeedback(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_FEEDBACK);
        statement.setString(FETCH_FEEDBACK_FACULTY_ID, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertFeedback(String userId, String userName, String feedback, String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_FEEDBACK);
        statement.registerOutParameter(OUTPUT, Types.VARCHAR);
        statement.setString(USER_ID, userId);
        statement.setString(USER_NAME, userName);
        statement.setString(FEEDBACK, feedback);
        statement.setString(FACULTY_ID, facultyId);
        statement.execute();

        return statement.getString(OUTPUT_MESSAGE);
    }
}
