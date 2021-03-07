package com.group9.server.CourseCreation;

import javax.sql.DataSource;
import java.util.List;

public interface ICoursePersistence {
    void createCourses(String course_id, String course_name, String course_credit, String course_faculty, String course_Department);
}
