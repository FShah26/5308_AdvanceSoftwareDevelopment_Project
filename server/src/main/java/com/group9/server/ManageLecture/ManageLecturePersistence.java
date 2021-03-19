package com.group9.server.ManageLecture;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;
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
        String query = "SELECT * FROM lecture WHERE faculty_Id='" + facultyId + "'";
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
        statement.setDate(4, new java.sql.Date(lecDate.getTime()) );
        statement.execute();

        return statement.getBoolean("isSuccessful");
    }

    @Override
    public boolean updateLecture(String lecId, String courseId, String lecAgenda, Date lecDate) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteLecture(String lectureId) throws SQLException {
        return false;
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = con.prepareCall("{call get_assigned_courses(?)}");
        statement.setString(1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
