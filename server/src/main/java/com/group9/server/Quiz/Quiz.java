package com.group9.server.Quiz;

import com.group9.server.Dashboard.IDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

@Component
public class Quiz implements IQuiz {
    IQuizLogic quizLogic;

    @Autowired
    public Quiz(IQuizLogic quizLogic) {
        this.quizLogic = quizLogic;
    }


//    @Qualifier("facultyDashboard")
//    @Autowired
//    IDashboard dashboard;

    @Override
    public void addQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) {
        String message = quizLogic.addQuestion(courseId, quizNumber, question, optionA, optionB, optionC, optionD, answer);
        System.out.println(message);

    }
    @Override
    public String getCourseId() {
        System.out.println("Enter the course ID: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getQuizNumber() {
        System.out.println("Enter the Quiz Number: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getQuestion() {
        System.out.println("Enter the Question: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getOptionA() {
        System.out.println("Enter the first option: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getOptionB() {
        System.out.println("Enter the second option: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getOptionC() {
        System.out.println("Enter the third option: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getOptionD() {
        System.out.println("Enter the fourth option: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    @Override
    public String getAnswer() {
        System.out.println("Enter the Answer: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
