package com.group9.server.Dashboard;

import com.group9.server.Feedback.IFeedback;
import com.group9.server.ManageLecture.IManageLecture;
import com.group9.server.Quiz.IQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyDashboard implements IDashboard {
    InputValidator validator;
    IFeedback feedback;
    IQuiz quiz;
    @Autowired
    IManageLecture manageLecture;
    private String username;

    @Autowired
    public FacultyDashboard(InputValidator validator, IFeedback feedback, IQuiz quiz) {
        this.validator = validator;
        this.feedback = feedback;
        this.quiz = quiz;
    }

    @Override
    public void dashboard() throws SQLException {

        System.out.println("************************************************");
        System.out.println("               FACULTY DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Notifications");
        System.out.println("Press 2 --> Manage Lectures");
        System.out.println("Press 3 --> Send Announcement");
        System.out.println("Press 4 --> Manage Meetings");
        System.out.println("Press 5 --> View Feedback");
        System.out.println("Press 6 --> Add question for a quiz");
        System.out.println("Press 7 --> Log out");
        System.out.println();
        selectMenu();
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public void selectMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    public void checkinput(String selection) throws SQLException {
        if (this.validator.validate(selection)) {
            switch (selection) {
                case "2":
                    manageLecture.showManageLectureMenu(this.username);
                    break;
                case "5":
                    feedback.viewFeedback(username);
                    break;
                case "6":
                    String courseId = quiz.getCourseId();
                    String quizNumber = quiz.getQuizNumber();
                    System.out.println("Enter the number of questions you want to add");
                    Scanner scanner = new Scanner(System.in);
                    int numberOfQuestions = scanner.nextInt();
                    for(int i = 0; i < numberOfQuestions; i++) {
                        String question = quiz.getQuestion();
                        String optionA = quiz.getOptionA();
                        String optionB = quiz.getOptionB();
                        String optionC = quiz.getOptionC();
                        String optionD = quiz.getOptionD();
                        String answer = quiz.getAnswer();
                        quiz.addQuestion(courseId, quizNumber, question, optionA, optionB, optionC, optionD, answer);
                    }
                    break;

                default:
                    System.out.println("Yet to develop..");
            }
            dashboard();
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
