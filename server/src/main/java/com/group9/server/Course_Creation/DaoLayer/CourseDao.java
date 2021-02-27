package com.group9.server.Course_Creation.DaoLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

@Component
public class CourseDao extends JdbcDaoSupport implements ICourseDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplateObject;

    @PostConstruct
    private void initialize(){setDataSource(dataSource);}

    public void setDataSource() {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void CreateCourses(String course_id,String course_name,String course_credit,String course_faculty,String course_Department) {
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(dataSource).withProcedureName("Create_NewCourse");
        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("course_id",course_id)
                .addValue("course_name", course_name)
                .addValue("Department", course_Department)
                .addValue("course_credit", course_credit)
                .addValue("course_Faculty", course_faculty);
        System.out.println();
        System.out.println("Please wait.. processing...");
        System.out.println();
        Map results = jdbcCall.execute(inParams);
        String message =(String)results.get("msg");
        System.out.println(message);
    }
}

