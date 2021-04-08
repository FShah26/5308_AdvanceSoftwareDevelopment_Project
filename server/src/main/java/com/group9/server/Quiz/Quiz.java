package com.group9.server.Quiz;

import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class Quiz implements IQuiz {
    IQuizLogic quizLogic;

    public Quiz(IQuizLogic quizLogic) {
        this.quizLogic = quizLogic;
    }


    @Override
    public void addQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) {
        String message = quizLogic.addQuestion(courseId, quizNumber, question, optionA, optionB, optionC, optionD, answer);
        System.out.println(message);

    }

    @Override
    public void createQuiz() {
        String courseId = getCourseId();
        String quizNumber = getQuizNumber();
        System.out.println("Enter the number of questions you want to add");
        Scanner scanner = new Scanner(System.in);
        int numberOfQuestions = scanner.nextInt();
        for (int i = 0; i < numberOfQuestions; i++) {
            String question = getQuestion();
            String optionA = getOptionA();
            String optionB = getOptionB();
            String optionC = getOptionC();
            String optionD = getOptionD();
            String answer = getAnswer();
            addQuestion(courseId, quizNumber, question, optionA, optionB, optionC, optionD, answer);
        }
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

    @Override
    public void execute(String userRole, String userId) {
        createQuiz();
    }
}
