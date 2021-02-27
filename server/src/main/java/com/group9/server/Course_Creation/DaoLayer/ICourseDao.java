package com.group9.server.Course_Creation.DaoLayer;

import javax.sql.DataSource;
import java.util.List;

public interface ICourseDao {
    void CreateCourses(String course_id,String course_name,String course_credit,String course_faculty,String course_Department);
}
