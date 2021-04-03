package com.group9.server.Dashboard;

import com.group9.server.Announcements.Admin.FacultyAnnouncement;
import com.group9.server.Announcements.Admin.IAnnouncementInput;
import com.group9.server.Feedback.IFeedback;
import com.group9.server.ManageLecture.IManageLecture;
import com.group9.server.Meeting.FacultyManageMeeting.IManageMeeting;
import com.group9.server.Notifications.ViewUserNotifications;
import com.group9.server.Quiz.IQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyDashboard implements IDashboard {
    InputValidator validator;
    IFeedback feedback;
    IQuiz quiz;
    IManageLecture manageLecture;
    ViewUserNotifications notifications;
    @Qualifier("facultyAnnouncement")
    @Autowired
    IAnnouncementInput announcementInput;
    @Autowired
    IManageMeeting manageMeeting;

    private String username;
    private String role;

    @Autowired
    public FacultyDashboard(InputValidator validator, IFeedback feedback, ViewUserNotifications notifications, IManageLecture manageLecture, IQuiz quiz) {
        this.validator = validator;
        this.feedback = feedback;
        this.notifications = notifications;
        this.manageLecture = manageLecture;
        this.quiz = quiz;
        this.role = "admin";
    }

    @Override
    public void dashboard() throws SQLException {

        out.println("************************************************");
        out.println("               FACULTY DASHBOARD                ");
        out.println("************************************************");

        out.println("Press 1 --> Notifications");
        out.println("Press 2 --> Manage Lectures");
        out.println("Press 3 --> Send Announcement");
        out.println("Press 4 --> Manage Meetings");
        out.println("Press 5 --> View Feedback");
        out.println("Press 6 --> Add question for a quiz");
        out.println("Press 7 --> Log out");
        out.println("Choose Option:");
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
                case "1":
                    notifications.displayAllNotifications(username);
                    break;
                case "2":
                    manageLecture.showManageLectureMenu(this.username);
                    break;
                case "3":
                    announcementInput.make_announcement(this.role,this.username);
                    break;
                case "4":
                    manageMeeting.display(username);
                    break;
                case "5":
                    feedback.viewFeedback(username);
                    break;
                case "6":
                    String courseId = quiz.getCourseId();
                    String quizNumber = quiz.getQuizNumber();
                    out.println("Enter the number of questions you want to add");
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
                case "7":
                    out.println("Logging out...");
                    out.println("Logged out successfully...");
                    System.exit(0);
                    break;
                default:
                    out.println("Yet to develop..");
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
