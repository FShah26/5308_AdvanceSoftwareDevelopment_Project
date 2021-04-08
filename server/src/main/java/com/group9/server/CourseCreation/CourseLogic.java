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
        String output;
        boolean validation;
        try {
            validation = validate.validateInput(courseId, courseCredit, courseFaculty);
            if (validation) {
                output = coursePersistence.createCourses(courseId, courseName, courseCredit, courseFaculty, courseDepartment);
            } else {
                output = "Validation Error : Please enter Course ID staring with \"CSCI\",CREDIT BETWEEN 1 TO 5 and valid faculty ID.";
            }
        } catch (Exception ex) {
            output = "Some error occurred..";
        }
        return output;
    }
}

