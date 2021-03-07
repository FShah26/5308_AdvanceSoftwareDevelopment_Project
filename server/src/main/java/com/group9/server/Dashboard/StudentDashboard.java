package com.group9.server.Dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class StudentDashboard implements IDashboard {
    InputValidator validator;

    @Autowired
    public StudentDashboard(InputValidator validator) {
        this.validator = validator;
    }

    @Override
    public void dashboard() {

        System.out.println("************************************************");
        System.out.println("               STUDENT DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Add Notes");
        System.out.println("Press 2 --> View Notes");
        System.out.println("Press 3 --> ");
        System.out.println("Press 4 --> ");
        System.out.println("Press 5 --> To Log Out.");
        System.out.println();
        selectMenu();
    }


    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection) {
        if (this.validator.validate(selection)) {
            out.println("Do something....");
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
