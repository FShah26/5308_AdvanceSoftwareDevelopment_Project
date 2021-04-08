package com.group9.server.CourseCreation;

import org.springframework.stereotype.Component;

@Component
public class CourseLogic implements ICourseLogic {
    private static final String INVALID_OUTPUT = "Validation Error : Please enter Course ID staring with \"CSCI\",CREDIT BETWEEN 1 TO 5 and valid faculty ID.";
    private static final String OUTPUT_ERROR = "Some error occurred..";
    IValidate validate;
    ICoursePersistence coursePersistence;

    public CourseLogic(IValidate validate, ICoursePersistence coursePersistence) {
        this.validate = validate;
        this.coursePersistence = coursePersistence;
    }

    @Override
    public String courseCreate(String courseId, String courseName, String courseCredit, String courseFaculty, String courseDepartment) {
        String output = "";
        boolean validation;
        try {
            validation = validate.validateInput(courseId, courseCredit, courseFaculty);
            if (validation) {
                output = coursePersistence.createCourses(courseId, courseName, courseCredit, courseFaculty, courseDepartment);
            } else {
                output = INVALID_OUTPUT;
            }
        } catch (Exception ex) {
            output = OUTPUT_ERROR;
        }
        return output;
    }
}

