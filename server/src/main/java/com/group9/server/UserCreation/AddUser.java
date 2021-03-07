package com.group9.server.UserCreation;

import com.group9.server.Dashboard.IAdminInputValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

import static java.lang.System.out;

public class AddUser {

    @Autowired
    IValidateAddUser validate;

    IAdminInputValidator inputValidator;

    @Autowired
    public AddUser() {
        this.inputValidator = new AdminAddUserConfirm();
    }

    @Autowired
    IAddUserLogic addUserService;
    String id;
    String userid;
    String password;
    String user_type;

    Scanner sc;
    public void creation() {
        System.out.println("************************************************");
        System.out.println("      ENTER DETAILS TO CREATE NEW USER        ");
        System.out.println("************************************************");
         sc = new Scanner(System.in);
        System.out.print("Enter id : ");
         id = sc.nextLine();
        System.out.print("Enter userid : ");
         userid = sc.nextLine();
        System.out.print("Enter password : ");
         password = sc.nextLine();
        System.out.print("Enter user_type : ");
         user_type = sc.nextLine();

        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
        selectMenu();

    }

    public void selectMenu() {
        String menuOption = sc.nextLine();
        validateInput(menuOption);
    }

    public void validateInput(String input) {
        if (this.inputValidator.validate(input)) {
            String output = validate.validate_input(id, userid, password, user_type);

            if (output.equals("true")) {
                addUserService.addUser(id, userid, password, user_type);
            } else
                System.out.println(output);
        }
        else
          {
              displayInvalidMenuOptionMsg();
              creation();
         }
}
    public void displayInvalidMenuOptionMsg(){
        out.println("Invalid Option! Please choose a valid option from above menu.");
    }

}
