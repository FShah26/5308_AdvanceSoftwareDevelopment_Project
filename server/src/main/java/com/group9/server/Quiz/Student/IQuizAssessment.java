package com.group9.server.Quiz.Student;

import com.group9.server.IExecuteAction;

public interface IQuizAssessment extends IExecuteAction {
    void showQuizMenu(String studentId);

    void assessQuiz();

    void viewQuiz();
}
