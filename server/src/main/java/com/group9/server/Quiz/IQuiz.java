package com.group9.server.Quiz;

import java.sql.SQLException;

public interface IQuiz {

    void addQuestion(String course_Id, String quizNumber, String question, String optionA, String optionB, String optionC, String optionD, String answer) throws SQLException;

    String getCourseId();
    String getQuizNumber();
    String getQuestion();
    String getOptionA();
    String getOptionB();
    String getOptionC();
    String getOptionD();
    String getAnswer();



}
