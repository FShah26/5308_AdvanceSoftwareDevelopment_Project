package com.group9.server.HomePage;

import com.group9.server.Login.IUserInputValidator;
import com.group9.server.Login.RoleValidator;
import org.springframework.stereotype.Component;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ApplicationHome implements IHomePage {
    private static final String ADMIN_SELECTION = "1";
    private static final String FACULTY_SELECTION = "2";
    private static final String STUDENT_SELECTION = "3";
    IUserInputValidator inputValidator;

    public ApplicationHome() {
        this.inputValidator = new RoleValidator();
    }

    @Override
    public void getMenu() {
        out.println("Welcome to the Course Management System");
        out.println("Please choose your category:");
        out.println("1 -> Admin");
        out.println("2 -> Faculty");
        out.println("3 -> Student");
        out.println("Please enter an option and press \"Enter\" key :");
    }

    @Override
    public String UserTypeSelectMenu() {
        Scanner scanner = new Scanner(System.in);
        String menuOption = scanner.nextLine();
        validateSelectedMenuOption(menuOption);
        switch (menuOption.trim()) {
            case ADMIN_SELECTION:
                return UserConstants.ADMIN;
            case FACULTY_SELECTION:
                return UserConstants.FACULTY;
            case STUDENT_SELECTION:
                return UserConstants.STUDENT;
        }
        return UserConstants.ADMIN;
    }

    @Override
    public void validateSelectedMenuOption(String menuOption) {
        if (!this.inputValidator.validate(menuOption)) {
            displayInvalidMenuOptionMsg();
            UserTypeSelectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option!");
        out.println("Please choose a valid option from above menu.");
    }
}
