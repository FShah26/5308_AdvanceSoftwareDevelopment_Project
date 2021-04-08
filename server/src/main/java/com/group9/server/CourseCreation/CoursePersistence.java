package com.group9.server.CourseCreation;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class CoursePersistence implements ICoursePersistence {

    private static final String CREATE_COURSE = "{call createNewCourse(?, ?, ?, ?, ?, ?)}";
    private static final int CREATE_COURSE_PARAMETER_INDEX_1 = 1;
    private static final int CREATE_COURSE_PARAMETER_INDEX_2 = 2;
    private static final int CREATE_COURSE_PARAMETER_INDEX_3 = 3;
    private static final int CREATE_COURSE_PARAMETER_INDEX_4 = 4;
    private static final int CREATE_COURSE_PARAMETER_INDEX_5 = 5;
    private static final int CREATE_COURSE_PARAMETER_INDEX_6 = 6;
    private static final String OUT_PARAMETER = "message";
    Connection connection;

    public CoursePersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String createCourses(String courseId, String courseName, String courseCredit, String courseFaculty, String courseDepartment) {
        String output = "";
        try {
            CallableStatement statement = connection.prepareCall(CREATE_COURSE);
            statement.registerOutParameter(CREATE_COURSE_PARAMETER_INDEX_6, Types.VARCHAR);
            statement.setString(CREATE_COURSE_PARAMETER_INDEX_1, courseId);
            statement.setString(CREATE_COURSE_PARAMETER_INDEX_2, courseName);
            statement.setString(CREATE_COURSE_PARAMETER_INDEX_3, courseDepartment);
            statement.setString(CREATE_COURSE_PARAMETER_INDEX_4, courseCredit);
            statement.setString(CREATE_COURSE_PARAMETER_INDEX_5, courseFaculty);
            statement.execute();
            output = statement.getString(OUT_PARAMETER);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
        return output;
    }
}

