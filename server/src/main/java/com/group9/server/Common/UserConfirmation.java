package com.group9.server.Common;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class UserConfirmation implements IUserConfirmation {

    private static final int CHECK_VALUE = 1;
    private static final boolean CHECK_VALIDATE = false;
    IUserInputValidator userConfirmationOptionValidator;

    public UserConfirmation(IUserInputValidator userConfirmationOptionValidator) {
        this.userConfirmationOptionValidator = userConfirmationOptionValidator;
    }

    @Override
    public void showUserConfirmationOptions() {
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
    }

    @Override
    public boolean getUserConfirmation() {
        showUserConfirmationOptions();
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        while (this.userConfirmationOptionValidator.validate(menuOption) == CHECK_VALIDATE) {
            System.out.println("Invalid Option! Please choose a valid option from menu.");
            showUserConfirmationOptions();
            menuOption = sc.nextLine();
        }
        return (Integer.parseInt(menuOption.trim()) == CHECK_VALUE);
    }
}
