package com.group9.server.Feedback;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class FeedbackPersistence implements IFeedbackPersistence {

    private static final int FEEDBACK_PARAMETER_INDEX_1 = 1;
    private static final int FEEDBACK_PARAMETER_INDEX_2 = 2;
    private static final int FEEDBACK_PARAMETER_INDEX_3 = 3;
    private static final int FEEDBACK_PARAMETER_INDEX_4 = 4;
    private static final int FEEDBACK_PARAMETER_INDEX_5 = 5;
    private static final String OUT_PARAMETER = "message";
    private static final String FETCH_FEEDBACK = "{call fetchFeedback(?)}";
    private static final String ADD_FEEDBACK = "{call addFeedback(?, ?, ?, ?, ?)}";
    Connection connection;

    public FeedbackPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchFeedback(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_FEEDBACK);
        statement.setString(FEEDBACK_PARAMETER_INDEX_1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertFeedback(String userId, String userName, String feedback, String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_FEEDBACK);
        statement.registerOutParameter(FEEDBACK_PARAMETER_INDEX_5, Types.VARCHAR);
        statement.setString(FEEDBACK_PARAMETER_INDEX_1, userId);
        statement.setString(FEEDBACK_PARAMETER_INDEX_2, userName);
        statement.setString(FEEDBACK_PARAMETER_INDEX_3, feedback);
        statement.setString(FEEDBACK_PARAMETER_INDEX_4, facultyId);
        statement.execute();

        return statement.getString(OUT_PARAMETER);
    }
}
