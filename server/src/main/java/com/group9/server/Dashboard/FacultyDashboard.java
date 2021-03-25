package com.group9.server.Dashboard;

import com.group9.server.Feedback.IFeedback;
import com.group9.server.ManageLecture.IManageLecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class    FacultyDashboard implements IDashboard {
    InputValidator validator;
    IFeedback feedback;
    @Autowired
    IManageLecture manageLecture;
    private String username;

    @Autowired
    public FacultyDashboard(InputValidator validator, IFeedback feedback) {
        this.validator = validator;
        this.feedback = feedback;
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
        if (this.validator.validate(selection)) {
            switch (selection) {
                case "2":
                    manageLecture.showManageLectureMenu(this.username);
                    break;
                case "5":
                    feedback.viewFeedback(username);
                    break;
                default:
                    System.out.println("Yet to develop..");
        }
        dashboard();
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
