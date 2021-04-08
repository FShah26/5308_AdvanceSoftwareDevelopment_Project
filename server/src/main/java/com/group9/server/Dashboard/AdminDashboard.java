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

    private static final String CREATE_COURSE ="1";
    private static final String ADD_USER ="2";
    private static final String ENROLL_STUDENT ="3";
    private static final String ANNOUNCEMENT ="4";
    private static final String LOGOUT ="5";
    private static final int SYSTEM_EXIT = 0;

    String userName;
    String userRole;

    IInputValidator IInputValidator;
    IExecuteAction createCourse;
    IExecuteAction announcementInput;
    IExecuteAction addUser;
    IExecuteAction enrollStudent;


    Map<String, IExecuteAction> action = new HashMap<>();

    public AdminDashboard(IExecuteAction announcementInput, IExecuteAction createCourse, IExecuteAction addUser, IExecuteAction enrollStudent) {
        this.IInputValidator = new AdminIInputValidator();
        this.userRole = UserConstants.ADMIN;
        this.announcementInput = announcementInput;
        this.createCourse = createCourse;
        this.addUser = addUser;
        this.enrollStudent = enrollStudent;
        action.put(CREATE_COURSE, this.createCourse);
        action.put(ADD_USER, this.addUser);
        action.put(ENROLL_STUDENT, this.enrollStudent);
        action.put(ANNOUNCEMENT, this.announcementInput);
        action.put(LOGOUT, null);
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
        if (this.IInputValidator.validate(selection)) {
            IExecuteAction dashboardAction = action.get(selection);
            if (null == dashboardAction) {
                out.println("Logging out...");
                out.println("Logged out successfully...");
                System.exit(SYSTEM_EXIT);
            } else {
                dashboardAction.execute(this.userRole, this.userName);
            }
            showDashboard();
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
