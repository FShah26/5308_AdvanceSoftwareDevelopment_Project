package com.group9.server.CourseCreation;

public interface ICoursePersistence {
    String createCourses(String course_id, String course_name, String course_credit, String course_faculty, String course_Department);
}
