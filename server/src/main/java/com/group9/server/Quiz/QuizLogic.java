package com.group9.server.Quiz;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class QuizLogic implements IQuizLogic {

    IQuizPersistence persistence;

    public QuizLogic(IQuizPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public String addQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) {
        String message = "";
        try {
            message = persistence.insertQuestion(courseId, quizNumber, question, optionA, optionB, optionC, optionD, answer);
        } catch (SQLException exception) {
            System.out.println("Adding question failed");
            exception.printStackTrace();
        }
        return message;
    }
}
