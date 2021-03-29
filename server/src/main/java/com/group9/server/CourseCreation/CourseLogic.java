package com.group9.server.CourseCreation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseLogic implements ICourseLogic {

    @Autowired
    IValidate validate;
    @Autowired
    ICoursePersistence courseDao;

    @Override
    public String courseCreate(String course_id, String course_name, String course_credit, String course_faculty, String course_Department) {
       String output="";
        try {
             output = validate.validate_input(course_id, course_credit, course_faculty);
            if (output.equals("true")) {
                output =  courseDao.createCourses(course_id, course_name, course_credit, course_faculty, course_Department);
            }
        }
        catch (Exception ex){
            output ="Some error occurred..";
        }
        return output;
    }
}

