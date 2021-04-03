package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Dashboard.AdminInputValidator;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class EnrollStudent implements IEnrollStudent {

    @Autowired
    IValidateEnrollStudent validate;
    @Qualifier("adminDashboard")
    @Autowired
    IDashboard dash;
    InputValidator inputValidator;
    @Autowired
    IEnrollStudentLogic enrollStudent;
    @Autowired
    IEnrollStudent iEnrollStudent;
    String userId;
    String courseId;
    String Term;
    Scanner scanner;

    public EnrollStudent() {
        this.inputValidator = new AdminInputValidator();
    }

    public void creation() throws SQLException {
        out.println("**********************************************************");
        out.println("      ENTER DETAILS TO ENROLL NEW STUDENT TO A COURSE     ");
        out.println("**********************************************************");
        scanner = new Scanner(System.in);
        out.print("Enter User ID : ");
        userId = scanner.nextLine();
        out.print("Enter Course ID : ");
        courseId = scanner.nextLine();
        out.print("Enter Term : ");
        Term = scanner.nextLine();

        out.println("-->Press 1 to confirm");
        out.println("-->Press 2 to Cancel");
        SelectMenu();

    }

    public void SelectMenu() throws SQLException {
        String menuOption = scanner.nextLine();
        ValidateInput(menuOption);
    }

    public void ValidateInput(String input) throws SQLException {
        if (this.inputValidator.validate(input)) {
            String output = validate.validateInput(userId, courseId, Term);
            final String TO_PROCEED = "true";
            if (output.equals(TO_PROCEED)) {
                enrollStudent.enrollStudent(userId, courseId, Term);
                dash.showDashboard();
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
