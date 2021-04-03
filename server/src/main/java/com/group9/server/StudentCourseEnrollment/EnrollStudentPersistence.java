package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Database.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class EnrollStudentPersistence implements IEnrollStudentPersistence {

    @Autowired
    DBConfig db;

    @Override
    public void enrollStudent(String userId, String courseId, String Term) {
        String dbURL = db.url;
        String user = db.user;
        String pass = db.password;
        String output="";
        final String STUDENT_COURSE_ENROLLMENT = "{call StudentCourseEnrollment(?, ?, ?, ?)}";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, pass);
                CallableStatement statement = conn.prepareCall(STUDENT_COURSE_ENROLLMENT);
        ) {
            statement.registerOutParameter(4, Types.VARCHAR);
            statement.setString(1, userId);
            statement.setString(2, courseId);
            statement.setString(3, Term);
            statement.execute();
            output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output ="Error Catched";
        }
        System.out.println(output);
    }
}

