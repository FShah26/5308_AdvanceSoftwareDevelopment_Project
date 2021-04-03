package com.group9.server.CourseCreation;

import com.group9.server.Database.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class CoursePersistence implements ICoursePersistence {

    @Autowired
    DBConfig db;

    @Override
    public String createCourses(String course_id, String course_name, String course_credit, String course_faculty, String course_Department) {
        String dbURL = db.url;
        String user = db.user;
        String password = db.password;
        String output = "";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call Create_NewCourse(?, ?, ?, ?, ?, ?)}")
        ) {

            statement.registerOutParameter(6, Types.VARCHAR);
            statement.setString(1, course_id);
            statement.setString(2, course_name);
            statement.setString(3, course_Department);
            statement.setString(4, course_credit);
            statement.setString(5, course_faculty);
            statement.execute();
            output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
        return output;
    }
}

