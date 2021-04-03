package com.group9.server.Feedback;

import com.group9.server.Database.ISingletonDatabase;
import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class FeedbackPersistence implements IFeedbackPersistence {
    Connection connection;

    @Autowired
    public FeedbackPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet fetchFeedback(String facultyId) throws SQLException {
        final String FETCH_FEEDBACK = "{call fetch_feedback(?)}";
        CallableStatement statement = connection.prepareCall(FETCH_FEEDBACK);
        statement.setString(1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertFeedback(String userId, String userName, String feedback, String facultyId) throws SQLException {
        final String ADD_FEEDBACK = "{call add_feedback(?, ?, ?, ?, ?)}";
        CallableStatement statement = connection.prepareCall(ADD_FEEDBACK);
        statement.registerOutParameter(5, Types.VARCHAR);
        statement.setString(1, userId);
        statement.setString(2, userName);
        statement.setString(3, feedback);
        statement.setString(4,facultyId);
        statement.execute();

        return statement.getString("message");
    }
}
