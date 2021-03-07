package com.group9.server.Dashboard;

import com.group9.server.CourseCreation.CreateCourse;
import com.group9.server.UserCreation.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class AdminDashboard implements IDashboard {

    String username;

    InputValidator inputValidator;
    @Autowired
    public AdminDashboard() {
        this.inputValidator = new AdminInputValidator();
    }
    @Autowired
    CreateCourse cc;
    @Autowired
    AddUser ac;

    @Override
    public void dashboard(){

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

    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    public void checkinput(String selection){
        if (this.inputValidator.validate(selection)) {
            if(selection.equals("1")){
                cc.creation();
            }
            else if(selection.equals("2")) {
                ac.creation();
            }
            else{
                System.out.println("Yet to develop..");
            }
        }
        else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }
    public void displayInvalidMenuOptionMsg(){
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
