package com.group9.server.Quiz;

public interface IQuizLogic {
    String addQuestion(String course_Id, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer);

}
