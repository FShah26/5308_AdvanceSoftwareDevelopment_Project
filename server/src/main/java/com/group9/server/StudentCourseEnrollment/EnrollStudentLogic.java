package com.group9.server.StudentCourseEnrollment;

import org.springframework.stereotype.Component;

@Component
public class EnrollStudentLogic implements IEnrollStudentLogic {

    IEnrollStudentPersistence enrollStudentPersistence;

    public void EnrollStudentLogic(IEnrollStudentPersistence enrollStudentPersistence)
    {
        this.enrollStudentPersistence = enrollStudentPersistence;
    }

    @Override
    public void enrollStudent(String userId, String courseId, String Term) {
        enrollStudentPersistence.enrollStudent(userId,courseId,Term);
    }
}

