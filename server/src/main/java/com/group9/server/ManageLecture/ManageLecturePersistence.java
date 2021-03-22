package com.group9.server.ManageLecture;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;
import java.util.TimeZone;

@Component
public class ManageLecturePersistence implements IManageLecturePersistence{


    DBConfig config;
    Connection con;

    @Autowired
    public ManageLecturePersistence(DBConfig config) throws SQLException
    {
        this.config=config;
        con= DriverManager.getConnection(config.url, config.user,config.password );
    }
    @Override
    public ResultSet getAllLectures(String facultyId) throws SQLException {
        String query = "SELECT * FROM lecture WHERE faculty_Id='" + facultyId + "' ORDER BY Date LIMIT 10 ";
        Statement statement = con.createStatement();
        ResultSet set = statement.executeQuery(query);
        return set;
    }

    @Override
    public boolean addLecture(String facultyId,String courseId, String lecTopic, Date lecDate) throws SQLException {
        CallableStatement statement = con.prepareCall("{call add_lecture(?, ?, ?, ?,?)}");
        statement.registerOutParameter(5, Types.BOOLEAN);
        statement.setString(1, facultyId);
        statement.setString(2, courseId);
        statement.setString(3, lecTopic);
        statement.setTimestamp(4, new java.sql.Timestamp(lecDate.getTime()) );
        statement.execute();

        return statement.getBoolean("isSuccessful");
    }

    @Override
    public boolean updateLecture(String lecId, String lecAgenda, Date lecDate) throws SQLException {
        CallableStatement statement = con.prepareCall("{call update_lecture(?, ?, ?, ?)}");
        statement.registerOutParameter(4, Types.BOOLEAN);
        statement.setString(1, lecId);
        statement.setString(2, lecAgenda);
        statement.setTimestamp(3, new java.sql.Timestamp(lecDate.getTime()) );
        statement.execute();
        return statement.getBoolean("isSuccessful");
    }

    @Override
    public boolean deleteLecture(String lectureId) throws SQLException {
        CallableStatement statement = con.prepareCall("{call delete_lecture(?, ?)}");
        statement.registerOutParameter(2, Types.BOOLEAN);
        statement.setString(1, lectureId);
        statement.execute();
        return statement.getBoolean("isSuccessful");
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = con.prepareCall("{call get_assigned_courses(?)}");
        statement.setString(1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    public ResultSet getCourseLectures(String courseId) throws SQLException{
        String query = "SELECT * FROM lecture WHERE course_Id='" + courseId + "' ORDER BY date desc LIMIT 15";
        Statement statement = con.createStatement();
        ResultSet set = statement.executeQuery(query);
        return set;
    }
}
