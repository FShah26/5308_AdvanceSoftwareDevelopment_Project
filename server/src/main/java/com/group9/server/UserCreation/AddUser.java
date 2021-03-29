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
    String userid;
    String password;
    String user_type;
    String name;
    String email_address;
    String department;
    Scanner sc;

    @Autowired
    public AddUser() {
        this.inputValidator = new AdminAddUserConfirm();
        this.dash = new AdminDashboard();
    }

    public void creation() throws SQLException {
        out.println("************************************************");
        out.println("      ENTER DETAILS TO CREATE NEW USER        ");
        out.println("************************************************");
        sc = new Scanner(System.in);
        out.print("Enter id : ");
        id = sc.nextLine();
        out.print("Enter userid : ");
        userid = sc.nextLine();
        out.print("Enter password : ");
        password = sc.nextLine();
        out.print("Enter user_type : ");
        user_type = sc.nextLine();
        out.print("Enter Name of the user : ");
        name = sc.nextLine();
        out.print("Enter email address of the student : ");
        email_address = sc.nextLine();
        out.print("Enter department : ");
        department = sc.nextLine();

        out.println("-->Press 1 to confirm");
        out.println("-->Press 2 to Cancel");
        selectMenu();

    }

    public void selectMenu() throws SQLException {
        String menuOption = sc.nextLine();
        validateInput(menuOption);
    }

    public void validateInput(String input) throws SQLException {
        if (this.inputValidator.validate(input)) {
            String output = validate.validate_input(id, userid, password, user_type);

            if (output.equals("true")) {
                addUserService.addUser(id, userid, password, user_type);
                addUserService.addUserDetails(userid, user_type, name, email_address, department);
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
