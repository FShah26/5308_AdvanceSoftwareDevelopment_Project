package com.group9.server.Notes;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class NotesPersistence implements INotesPersistence{

    DBConfig config;
    Connection con;

    @Autowired
    public NotesPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchNotes(String studentID, String courseID) throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_notes(?, ?)}");
        statement.setString(1, studentID);
        statement.setString(2, courseID);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public void insertNotes(String studentID, String courseID, String notes) {

    }
}
