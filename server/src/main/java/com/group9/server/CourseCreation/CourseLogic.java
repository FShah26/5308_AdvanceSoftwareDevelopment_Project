package com.group9.server.CourseCreation;

import org.springframework.stereotype.Component;

@Component
public class CourseLogic implements ICourseLogic {

    IValidate validate;
    ICoursePersistence coursePersistence;

    public CourseLogic(IValidate validate, ICoursePersistence coursePersistence) {
        this.validate = validate;
        this.coursePersistence = coursePersistence;
    }

    @Override
    public String courseCreate(String courseId, String courseName, String courseCredit, String courseFaculty, String courseDepartment) {
        String output = "";
        try {
            output = validate.validateInput(courseId, courseCredit, courseFaculty);
            if (output.equals("true")) {
                output = coursePersistence.createCourses(courseId, courseName, courseCredit, courseFaculty, courseDepartment);
            }
        } catch (Exception ex) {
            output = "Some error occurred..";
        }
        return output;
    }
}

