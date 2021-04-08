package com.group9.server.Quiz.Student;

public class ViewGrades {
    String courseId;
    String quizId;
    String grades;
    String attempt;
    String lastAttempt;


    public ViewGrades(String courseId, String quizId, String grades, String attempt, String lastAttempt) {
        this.courseId = courseId;
        this.quizId = quizId;
        this.grades = grades;
        this.attempt = attempt;
        this.lastAttempt = lastAttempt;
    }
}
