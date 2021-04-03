package com.group9.server.UserCreation;


import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

public class AddUser {

    @Autowired
    IValidateAddUser validate;
    @Qualifier("adminDashboard")
    @Autowired
    IDashboard dash;
    InputValidator inputValidator;
    @Autowired
    IAddUserLogic addUserService;
    String id;
    String userId;
    String password;
    String userType;
    String name;
    String emailAddress;
    String department;
    Scanner scanner;

    @Autowired
    public AddUser() {
        this.inputValidator = new AdminAddUserConfirm();
        this.dash = new AdminDashboard();
    }

    public void creation() throws SQLException {
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

    public void selectMenu() throws SQLException {
        String menuOption = scanner.nextLine();
        validateInput(menuOption);
    }

    public void validateInput(String input) throws SQLException {
        if (this.inputValidator.validate(input)) {
            String output = validate.validateInput(id, userId, password, userType);
            final String TO_PROCEED = "true";
            if (output.equals(TO_PROCEED)) {
                addUserService.addUser(id, userId, password, userType);
                addUserService.addUserDetails(userId, userType, name, emailAddress, department);
                dash.dashboard();
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

}
