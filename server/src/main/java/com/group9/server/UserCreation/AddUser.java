package com.group9.server.UserCreation;


import com.group9.server.Dashboard.InputValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class AddUser implements IAddUser {

    IValidateAddUser validateAddUser;
    InputValidator inputValidator;
    IAddUserLogic addUserService;
    String id;
    String userId;
    String password;
    String userType;
    String name;
    String emailAddress;
    String department;
    Scanner scanner;

    public AddUser(IAddUserLogic addUserService, IValidateAddUser validateAddUser) {
        this.inputValidator = new AdminAddUserConfirm();
        this.addUserService = addUserService;
        this.validateAddUser = validateAddUser;
    }

    public void creation() {
        out.println("************************************************");
        out.println("      ENTER DETAILS TO CREATE NEW USER        ");
        out.println("************************************************");
        scanner = new Scanner(System.in);
        out.print("Enter id : ");
        id = scanner.nextLine();
        out.print("Enter userid : ");
        userId = scanner.nextLine();
        out.print("Enter password : ");
        password = scanner.nextLine();
        out.print("Enter user_type : ");
        userType = scanner.nextLine();
        out.print("Enter Name of the user : ");
        name = scanner.nextLine();
        out.print("Enter email address of the student : ");
        emailAddress = scanner.nextLine();
        out.print("Enter department : ");
        department = scanner.nextLine();

        out.println("-->Press 1 to confirm");
        out.println("-->Press 2 to Cancel");
        selectMenu();

    }

    public void selectMenu() {
        String menuOption = scanner.nextLine();
        validateInput(menuOption);
    }

    public void validateInput(String input) {
        if (this.inputValidator.validate(input)) {
            String output = validateAddUser.validateInput(id, userId, password, userType);
            final String TO_PROCEED = "true";
            if (output.equals(TO_PROCEED)) {
                addUserService.addUser(id, userId, password, userType);
                addUserService.addUserDetails(userId, userType, name, emailAddress, department);
            } else
                out.println(output);
        } else {
            displayInvalidMenuOptionMsg();
            creation();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from above menu.");
    }

    @Override
    public void execute(String userRole, String userId) {
        creation();
    }
}
