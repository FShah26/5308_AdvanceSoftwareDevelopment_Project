package com.group9.server.Course_Creation;

import com.group9.server.UserInputValidations.Interface.IAdminInputValidator;
import com.group9.server.UserInputValidations.Interface.IUserInputValidator;
import com.group9.server.UserInputValidations.Validators.AdminInputValidator;
import com.group9.server.UserInputValidations.Validators.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class AdminDashboard {

    IAdminInputValidator inputValidator;
    @Autowired
    public AdminDashboard() {
        this.inputValidator = new AdminInputValidator();
    }
    @Autowired
    CreateCourse cc;

    public void dashboard(){

        Scanner sc = new Scanner(System.in);

        System.out.println("************************************************");
        System.out.println("                 ADMIN DASHBOARD                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Enter Course and Assign Faculty.");
        System.out.println("Press 2 --> Add New User.");
        System.out.println("Press 3 --> Student Course Enrollment.");
        System.out.println("Press 4 --> Making General Announcement.");
        System.out.println("Press 5 --> To Log Out.");
        System.out.println();
        SelectMenu();
    }

    public void SelectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    public void checkinput(String selection){
        if (this.inputValidator.validate(selection)) {
            cc.creation();
        }
        else {
            displayInvalidMenuOptionMsg();
            SelectMenu();
        }
    }
    public void displayInvalidMenuOptionMsg(){
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
