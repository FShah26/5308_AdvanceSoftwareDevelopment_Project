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
public class EnrollStudent {

    @Autowired
    IValidateEnrollStudent validate;
    @Qualifier("adminDashboard")
    @Autowired
    IDashboard dash;
    InputValidator inputValidator;
    @Autowired
    IEnrollStudentLogic enrollstudent;
    String userId;
    String courseId;
    String Term;
    Scanner sc;

    public EnrollStudent() {
        this.inputValidator = new AdminInputValidator();
    }

    public void creation() throws SQLException {
        out.println("**********************************************************");
        out.println("      ENTER DETAILS TO ENROLL NEW STUDENT TO A COURSE     ");
        out.println("**********************************************************");
        sc = new Scanner(System.in);
        out.print("Enter User ID : ");
        userId = sc.nextLine();
        out.print("Enter Course ID : ");
        courseId = sc.nextLine();
        out.print("Enter Term : ");
        Term = sc.nextLine();

        out.println("-->Press 1 to confirm");
        out.println("-->Press 2 to Cancel");
        SelectMenu();

    }

    public void SelectMenu() throws SQLException {
        String menuOption = sc.nextLine();
        ValidateInput(menuOption);
    }

    public void ValidateInput(String input) throws SQLException {
        if (this.inputValidator.validate(input)) {
            String output = validate.validate_input(userId, courseId, Term);

            if (output.equals("true")) {
                enrollstudent.enrollStudent(userId, courseId, Term);
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
