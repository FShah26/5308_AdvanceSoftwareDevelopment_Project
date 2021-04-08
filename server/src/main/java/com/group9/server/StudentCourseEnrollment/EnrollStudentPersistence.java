package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class EnrollStudentPersistence implements IEnrollStudentPersistence {
    final String STUDENT_COURSE_ENROLLMENT = "{call studentCourseEnrollments(?, ?, ?, ?)}";
    private static final int ENROLL_STUDENT_PARAMETER_INDEX_1 = 1;
    private static final int ENROLL_STUDENT_PARAMETER_INDEX_2 = 2;
    private static final int ENROLL_STUDENT_PARAMETER_INDEX_3 = 3;
    private static final int ENROLL_STUDENT_PARAMETER_INDEX_4 = 4;
    private static final String RETURN_MESSAGE = "message";
    Connection connection;

    public EnrollStudentPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public void enrollStudent(String userId, String courseId, String Term) {
        String output;
        try (
                CallableStatement statement = connection.prepareCall(STUDENT_COURSE_ENROLLMENT)
        ) {
            statement.registerOutParameter(ENROLL_STUDENT_PARAMETER_INDEX_4, Types.VARCHAR);
            statement.setString(ENROLL_STUDENT_PARAMETER_INDEX_1, userId);
            statement.setString(ENROLL_STUDENT_PARAMETER_INDEX_2, courseId);
            statement.setString(ENROLL_STUDENT_PARAMETER_INDEX_3, Term);
            statement.execute();
            output = statement.getString(RETURN_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error Catched");
        }
    }
}

