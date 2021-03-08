package com.group9.server.StudentCourseEnrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnrollStudentLogic implements IEnrollStudentLogic {

    @Autowired
    IEnrollStudentPersistence enrollStudentPersistence;

    @Override
    public void enrollStudent(String userId, String courseId, String Term) {
        enrollStudentPersistence.enrollStudent(userId,courseId,Term);
    }
}

