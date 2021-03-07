package com.group9.server.HomePage;

import com.group9.server.Login.IUserInputValidator;
import com.group9.server.Login.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ApplicationHome implements IHomePage {

    IUserInputValidator inputValidator;

    @Autowired
    public ApplicationHome() {
        this.inputValidator = new RoleValidator();
    }

    @Override
    public void GetMenu() {
        out.println("Welcome to the Course Management System");
        out.println("Please choose your category:");
        out.println("1 -> Admin");
        out.println("2 -> Faculty");
        out.println("3 -> Student");
        out.println("* -> Configure database");
        out.println("Please enter an option and press \"Enter\" key :");
    }

    @Override
    public String SelectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        ValidateSelectedMenuOption(menuOption);
        switch (menuOption.trim()) {
            case "1":
                return "admin";
            case "2":
                return "faculty";
            case "3":
                return "student";
            default:
                return "admin";
        }
    }

    @Override
    public void ValidateSelectedMenuOption(String menuOption) {
        if (!this.inputValidator.validate(menuOption)) {
            displayInvalidMenuOptionMsg();
            SelectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option!");
        out.println("Please choose a valid option from above menu.");
    }


}
