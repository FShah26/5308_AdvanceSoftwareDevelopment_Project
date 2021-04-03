package com.group9.server.Dashboard;

import com.group9.server.HomePage.UserConstants;
import com.group9.server.IExecuteAction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class AdminDashboard implements IDashboard {

    String userName;
    String userRole;

    InputValidator inputValidator;
    IExecuteAction createCourse;
    IExecuteAction announcementInput;
    IExecuteAction addUser;
    IExecuteAction enrollStudent;

    Map<String, IExecuteAction> action = new HashMap<>();

    public AdminDashboard(IExecuteAction announcementInput, IExecuteAction createCourse, IExecuteAction addUser, IExecuteAction enrollStudent) {
        this.inputValidator = new AdminInputValidator();
        this.userRole = UserConstants.ADMIN;
        this.announcementInput = announcementInput;
        this.createCourse = createCourse;
        this.addUser = addUser;
        this.enrollStudent = enrollStudent;
        action.put("1", this.createCourse);
        action.put("2", this.addUser);
        action.put("3", this.enrollStudent);
        action.put("4", this.announcementInput);
        action.put("5", null);
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

    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection) {
        if (this.inputValidator.validate(selection)) {
            IExecuteAction dashboardAction = action.get(selection);
            if (null == dashboardAction) {
                //logout
            } else {
                dashboardAction.execute(this.userRole, this.userName);
            }

        }
        showDashboard();
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
