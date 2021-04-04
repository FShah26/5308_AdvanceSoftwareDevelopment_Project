package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class EnrollStudentPersistence implements IEnrollStudentPersistence {
    final String STUDENT_COURSE_ENROLLMENT = "{call StudentCourseEnrollment(?, ?, ?, ?)}";
    Connection connection;

    public EnrollStudentPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public void enrollStudent(String userId, String courseId, String Term) {
        String output = "";
        try (
                CallableStatement statement = connection.prepareCall(STUDENT_COURSE_ENROLLMENT)
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
            output = "Error Catched";
        }
        System.out.println(output);
    }
}

