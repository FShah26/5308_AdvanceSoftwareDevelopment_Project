package com.group9.server.Meeting.Faculty;

import com.group9.server.cnfg.DBConfig;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ManageMeetingPersistence implements IManageMeetingPersistence{
    DBConfig config;
    Connection con;

    public ManageMeetingPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet meetingLog(String facultyId,String facultySelection) throws SQLException {
        CallableStatement statement = con.prepareCall("{call showfacultymeetings(?,?)}");
        statement.setString(1, facultySelection);
        statement.setString(2, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }
    @Override
    public String responseMeeting(int meetingId,String decision, String response) throws SQLException {
        CallableStatement statement = con.prepareCall("{call meeting_response(?,?,?,?)}");
        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setInt(1, meetingId);
        statement.setString(2, decision);
        statement.setString(3, response);
        statement.execute();
        String output = statement.getString("msg");
        return output;
    }
}
