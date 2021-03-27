package com.group9.server.Dashboard;

import com.group9.server.Announcements.Admin.IAnnouncementInput;
import com.group9.server.CourseCreation.CreateCourse;
import com.group9.server.StudentCourseEnrollment.EnrollStudent;
import com.group9.server.UserCreation.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class AdminDashboard implements IDashboard {

    String username;
    String userrole;

    InputValidator inputValidator;
    @Autowired
    IAnnouncementInput announcement;
    @Autowired
    CreateCourse cc;
    @Autowired
    AddUser ac;
    @Autowired
    EnrollStudent es;
    public AdminDashboard() {
        this.inputValidator = new AdminInputValidator();
        this.userrole ="admin";
    }

    @Override
    public void dashboard() throws SQLException {

        System.out.println("************************************************");
        System.out.println("                 ADMIN DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Enter Course and Assign Faculty.");
        System.out.println("Press 2 --> Add New User.");
        System.out.println("Press 3 --> Student Course Enrollment.");
        System.out.println("Press 4 --> Making General Announcement.");
        System.out.println("Press 5 --> To Log Out.");
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
        if (this.inputValidator.validate(selection)) {
            if (selection.equals("1")) {
                cc.creation();
            } else if (selection.equals("2")) {
                ac.creation();
            }
            else if(selection.equals("3")){
                es.creation();
            }
            else if(selection.equals("4")){
                    announcement.make_announcement(userrole,username);}
            else {
                System.out.println("Yet to develop..");
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
