package com.group9.server.Meeting;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class RequestMeetingPersistence implements IRequestMeetingPersistence {
    DBConfig config;
    Connection con;

    public RequestMeetingPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentID) throws SQLException {
        CallableStatement statement = con.prepareCall("{call RegisteredCourses(?)}");
        statement.setString(1, studentID);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public String setMeeting(String courseId, String studentID, String reason) throws SQLException {
        CallableStatement statement = con.prepareCall("{call RequestMeeting_Student(?,?,?,?)}");

        statement.registerOutParameter(4, Types.VARCHAR);
        statement.setString(1, studentID);
        statement.setString(2, courseId);
        statement.setString(3, reason);
        statement.execute();
        String output = statement.getString("msg");
        return output;
    }

    @Override
    public ResultSet viewMeeting(String studentId) throws SQLException {
        CallableStatement statement = con.prepareCall("{call ViewMeetingStatus(?)}");
        statement.setString(1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
