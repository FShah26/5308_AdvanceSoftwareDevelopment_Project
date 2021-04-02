package com.group9.server.Quiz.Student;

import java.sql.Timestamp;
import java.util.List;

public interface IQuizAssessmentLogic {
    void startQuiz(String courseId, String studentId, String quizNumber);

    int validateCourseId(String studentId, String courseId);

    boolean viewQuizForCourse(String courseId);

    boolean validateQuizNo(String courseId, String quizNumber);

    boolean updateGrades(String courseId, String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptTimestamp);

    double computeGrades(List<QuizQuestions> lstQuestions);

    void viewGrades(String studentId);
}
