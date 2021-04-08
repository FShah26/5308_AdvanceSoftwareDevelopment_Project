package com.group9.server.CourseCreation;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class CoursePersistence implements ICoursePersistence {

    private static final String CREATE_COURSE = "{call createNewCourse(?, ?, ?, ?, ?, ?)}";
    private static final int PARAMETER_INDEX_1 = 1;
    private static final int PARAMETER_INDEX_2 = 2;
    private static final int PARAMETER_INDEX_3 = 3;
    private static final int PARAMETER_INDEX_4 = 4;
    private static final int PARAMETER_INDEX_5 = 5;
    private static final int PARAMETER_INDEX_6 = 6;
    private static final String  PARAMETER_NAME = "message";
    private static final String  CATCH_OUTPUT = "Error Catched";
    Connection connection;

    public CoursePersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String createCourses(String courseId, String courseName, String courseCredit, String courseFaculty, String courseDepartment) {
        String output = "";
        try {
            CallableStatement statement = connection.prepareCall(CREATE_COURSE);
            statement.registerOutParameter(PARAMETER_INDEX_6, Types.VARCHAR);
            statement.setString(PARAMETER_INDEX_1, courseId);
            statement.setString(PARAMETER_INDEX_2, courseName);
            statement.setString(PARAMETER_INDEX_3, courseDepartment);
            statement.setString(PARAMETER_INDEX_4, courseCredit);
            statement.setString(PARAMETER_INDEX_5, courseFaculty);
            statement.execute();
            output = statement.getString(PARAMETER_NAME);
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = CATCH_OUTPUT;
        }
        return output;
    }
}

