package com.group9.server.CourseCreation;

public interface ICoursePersistence {
    String createCourses(String courseId, String courseName, String courseCredit, String courseFaculty, String courseDepartment);
}
