package com.group9.server.Quiz;

import com.group9.server.IExecuteAction;

public interface IQuiz extends IExecuteAction {

    void addQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String optionC, String optionD, String answer);

    void createQuiz();

    String getCourseId();

    String getQuizNumber();

    String getQuestion();

    String getOptionA();

    String getOptionB();

    String getOptionC();

    String getOptionD();

    String getAnswer();


}
