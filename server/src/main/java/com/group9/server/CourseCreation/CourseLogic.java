package com.group9.server.CourseCreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseLogic implements ICourseLogic {

    @Autowired
    ICoursePersistence courseDao;

    @Override
    public void courseCreate(String course_id, String course_name, String course_credit, String course_faculty, String course_Department) {
        courseDao.createCourses(course_id,course_name,course_credit,course_faculty,course_Department);
    }
}

