package com.group9.server.Dashboard;

import com.group9.server.Announcements.Student.ViewAnnouncements;
import com.group9.server.Feedback.IFeedback;
import com.group9.server.Meeting.IRequestMeeting;
import com.group9.server.Notes.IStudentNotes;
import com.group9.server.UpcomingLecture.UpcomingLecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class StudentDashboard implements IDashboard {
    InputValidator validator;
    IStudentNotes notes;
    @Autowired
    IRequestMeeting meeting;
    private String username;
    IFeedback feedback;
    ViewAnnouncements announcements;
    @Autowired
    UpcomingLecture lecture;

    @Autowired
    public StudentDashboard(InputValidator validator, IStudentNotes notes, IFeedback feedback, ViewAnnouncements announcements) {
        this.validator = validator;
        this.notes = notes;
        this.feedback = feedback;
        this.announcements = announcements;
    }

    @Override
    public void dashboard() throws SQLException {

        System.out.println("************************************************");
        System.out.println("               STUDENT DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Notifications");
        System.out.println("Press 2 --> Up-coming lectures");
        System.out.println("Press 3 --> Announcements");
        System.out.println("Press 4 --> View Notes");
        System.out.println("Press 5 --> Add Notes");
        System.out.println("Press 6 --> Attendance");
        System.out.println("Press 7 --> Request Meeting");
        System.out.println("Press 8 --> Send Feedback");
        System.out.println("Press 9 --> Tests");
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
        checkInput(menuOption);
    }

    public void checkInput(String selection) throws SQLException {
        String course;

        if (this.validator.validate(selection)) {
            switch (selection) {
                case "2":
                    lecture.lectureDisplay(username);
                    break;
                case "3":
                    announcements.displayAllAnnouncements();
                    break;

                case "4":
                    course = notes.getCourseInput();
                    notes.viewNotes(username, course);
                    break;

                case "5":
                    course = notes.getCourseInput();
                    String text = notes.getNotesText();

                    notes.addNotes(username, course, text);
                    break;

                case "7":
                    meeting.meetingDisplay(username);
                    break;
                case "8":
                    String student_name = feedback.getStudentName();
                    String fb = feedback.getFeedbackText();
                    String faculty = feedback.getFacultyID();

                    feedback.addFeedback(username, student_name, fb, faculty);
                    break;

                default:
                    out.println("Coming up!");
            }
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
