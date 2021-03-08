package com.group9.server.StudentCourseEnrollment;

public interface IEnrollStudentPersistence {
    void enrollStudent(String userId, String courseId, String Term);

}
