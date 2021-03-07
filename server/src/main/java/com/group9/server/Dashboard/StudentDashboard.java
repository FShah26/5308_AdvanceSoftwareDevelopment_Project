package com.group9.server.Dashboard;

import com.group9.server.Notes.IStudentNotes;
import com.group9.server.Notes.StudentNotes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class StudentDashboard implements IDashboard {
    InputValidator validator;
    IStudentNotes notes;

    @Autowired
    public StudentDashboard(InputValidator validator, IStudentNotes notes) {
        this.validator = validator;
        this.notes = notes;
    }

    @Override
    public void dashboard() {

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


    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection) {
        if (this.validator.validate(selection)) {
            switch (selection){
                case "4":
                    notes.viewNotes("hashik", "LJJSKDFJ");
                    break;

                case "5":
                    out.println("Add Notes");
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
