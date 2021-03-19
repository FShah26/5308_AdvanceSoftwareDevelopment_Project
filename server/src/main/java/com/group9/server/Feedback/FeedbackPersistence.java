package com.group9.server.Feedback;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class FeedbackPersistence implements IFeedbackPersistence {

    DBConfig config;
    Connection con;

    @Autowired
    public FeedbackPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchFeedback(String faculty_id) throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_feedback(?)}");
        statement.setString(1, faculty_id);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertFeedback(String user_id, String userName, String feedback, String faculty_id) throws SQLException {
        CallableStatement statement = con.prepareCall("{call add_feedback(?, ?, ?, ?, ?)}");
        statement.registerOutParameter(5, Types.VARCHAR);
        statement.setString(1, user_id);
        statement.setString(2, userName);
        statement.setString(3, feedback);
        statement.setString(4,faculty_id);
        statement.execute();

        return statement.getString("message");
    }
}
