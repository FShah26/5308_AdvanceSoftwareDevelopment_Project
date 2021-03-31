package com.group9.server.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IQuizPersistence {
    String insertQuestion(String course_Id, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) throws SQLException;

    ResultSet fetchQuiz(String courseId, String quizNumber) throws SQLException;

    ResultSet fetchCourseQuiz(String courseId) throws SQLException;

    ResultSet fetchRegisteredCourses(String studentID) throws SQLException;
}
