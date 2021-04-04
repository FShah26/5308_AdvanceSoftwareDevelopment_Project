package com.group9.server.StudentCourseEnrollment;

import org.springframework.stereotype.Component;

@Component
public class EnrollStudentLogic implements IEnrollStudentLogic {

    IEnrollStudentPersistence persistence;

    public EnrollStudentLogic(IEnrollStudentPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public String enrollStudent(String userId, String courseId, String term) {
        String message = "";
        try {
            persistence.enrollStudent(userId, courseId, term);
        } catch (Exception e) {
            System.out.println("Adding feedback failed");
            e.printStackTrace();
        }    return message;
    }

}

