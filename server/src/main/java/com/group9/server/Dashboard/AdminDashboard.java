package com.group9.server.Dashboard;

import com.group9.server.Announcements.Admin.IAnnouncementInput;
import com.group9.server.CourseCreation.ICreateCourse;
import com.group9.server.StudentCourseEnrollment.EnrollStudent;
import com.group9.server.UserCreation.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class AdminDashboard implements IDashboard {

    String userName;
    String userRole;

    InputValidator inputValidator;
    @Qualifier("announcementInput")
    @Autowired
    IAnnouncementInput announcement;
    @Autowired
    ICreateCourse createCourse;
    @Autowired
    AddUser ac;
    @Autowired
    EnrollStudent es;

    public AdminDashboard() {
        this.inputValidator = new AdminInputValidator();
        this.userRole = "admin";
    }

    @Override
    public void showDashboard() {
        out.println("************************************************");
        out.println("                 ADMIN DASHBOARD                ");
        out.println("************************************************");

        out.println("Press 1 --> Enter Course and Assign Faculty.");
        out.println("Press 2 --> Add New User.");
        out.println("Press 3 --> Student Course Enrollment.");
        out.println("Press 4 --> Making General Announcement.");
        out.println("Press 5 --> To Log Out.");
        out.println();
        selectMenu();
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    public void selectMenu()  {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection)  {
        if (this.inputValidator.validate(selection)) {
            if (selection.equals("1")) {
                createCourse.creation();
            } else if (selection.equals("2")) {
                ac.creation();
            } else if (selection.equals("3")) {
                es.creation();
            } else if (selection.equals("4")) {
                announcement.announcement(userRole, userName);
            } else {
                out.println("Yet to develop..");
            }
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
        showDashboard();
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
