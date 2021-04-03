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

    final String CREATE_COURSE = "{call Create_NewCourse(?, ?, ?, ?, ?, ?)}";
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
            statement.registerOutParameter(6, Types.VARCHAR);
            statement.setString(1, courseId);
            statement.setString(2, courseName);
            statement.setString(3, courseDepartment);
            statement.setString(4, courseCredit);
            statement.setString(5, courseFaculty);
            statement.execute();
            output = statement.getString("message");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
        return output;
    }
}

