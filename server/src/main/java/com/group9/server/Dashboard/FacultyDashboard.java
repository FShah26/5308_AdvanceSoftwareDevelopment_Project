package com.group9.server.Dashboard;

import com.group9.server.Announcements.IAnnouncementInput;
import com.group9.server.CourseCreation.CreateCourse;
import com.group9.server.StudentCourseEnrollment.EnrollStudent;
import com.group9.server.UserCreation.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyDashboard implements IDashboard{
    String username;

    InputValidator inputValidator;

    public FacultyDashboard() {
        this.inputValidator = new FacultyValidator();
    }

    @Override
    public void dashboard() {

        System.out.println("************************************************");
        System.out.println("               FACULTY DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Notifications");
        System.out.println("Press 2 --> Manage Lectures");
        System.out.println("Press 3 --> Send Announcement");
        System.out.println("Press 4 --> Manage Meetings");
        System.out.println("Press 5 --> View Feedback");
        System.out.println("Press 6 --> Log out");
        System.out.println();
        selectMenu();
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    public void checkinput(String selection) {
        if (this.inputValidator.validate(selection)) {
          System.out.println("Yet to develop..");
        }
        else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
