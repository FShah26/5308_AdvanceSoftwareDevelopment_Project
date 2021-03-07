package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Dashboard.IAdminInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class EnrollStudent {

    @Autowired
    IValidateEnrollStudent validate;

    IAdminInputValidator inputValidator;


    public EnrollStudent() {
        this.inputValidator = new AdminEnrollStudentConfirm();
    }

    @Autowired
    IEnrollStudentLogic enrollstudent;
    String userId;
    String courseId;
    String Term;

    Scanner sc;
    public void creation() {
        System.out.println("**********************************************************");
        System.out.println("      ENTER DETAILS TO ENROLL NEW STUDENT TO A COURSE     ");
        System.out.println("**********************************************************");
        sc = new Scanner(System.in);
        System.out.print("Enter User ID : ");
        userId = sc.nextLine();
        System.out.print("Enter Course ID : ");
        courseId = sc.nextLine();
        System.out.print("Enter Term : ");
        Term = sc.nextLine();

        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
        SelectMenu();

    }

    public void SelectMenu() {
        String menuOption = sc.nextLine();
        ValidateInput(menuOption);
    }

    public void ValidateInput(String input) {
        if (this.inputValidator.validate(input)) {
            String output = validate.validate_input(userId, courseId, Term);

            if (output.equals("true")) {
                enrollstudent.enrollStudent(userId, courseId, Term);
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
