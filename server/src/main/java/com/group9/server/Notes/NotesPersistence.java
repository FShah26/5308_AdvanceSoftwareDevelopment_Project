package com.group9.server.Notes;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class NotesPersistence implements INotesPersistence {

    DBConfig config;
    Connection con;

    @Autowired
    public NotesPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchNotes(String studentId, String courseId) throws SQLException {
        final String FETCH_NOTES = "{call fetch_notes(?, ?)}";
        CallableStatement statement = con.prepareCall(FETCH_NOTES);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String insertNotes(String studentId, String courseId, String notes) throws SQLException {
        final String ADD_NOTES = "{call add_notes(?, ?, ?, ?)}";
        CallableStatement statement = con.prepareCall(ADD_NOTES);
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setString(1, studentId);
        statement.setString(2, courseId);
        statement.setString(3, notes);
        statement.execute();

        return statement.getString("message");
    }
}
