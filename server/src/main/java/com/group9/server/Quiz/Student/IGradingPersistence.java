package com.group9.server.Quiz.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public interface IGradingPersistence {
    boolean addStudentGrades(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException;

    boolean updateStudentGrades(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException;

    ResultSet fetchPreviousGrades(String courseId, String studentId, String quizNumber) throws SQLException;
}
