package com.group9.server.Quiz;

public interface IQuizLogic {
    String addQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer);

}
