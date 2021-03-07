package com.group9.server.CourseCreation;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

@Component
public class CoursePersistence implements ICoursePersistence {

    @Autowired
    DBConfig db;

    @Override
    public void createCourses(String course_id, String course_name, String course_credit, String course_faculty, String course_Department) {
        String dbURL = db.url;
        String user = db.user;
        String password = db.password;
        String output="";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call Create_NewCourse(?, ?, ?, ?, ?, ?)}");
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
            output ="Error Catched";
        }
         System.out.println(output);
    }
}

