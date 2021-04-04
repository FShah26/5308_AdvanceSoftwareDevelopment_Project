package com.group9.server.StudentCourseEnrollment;

import com.group9.server.Dashboard.AdminInputValidator;
import com.group9.server.Dashboard.InputValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class EnrollStudent implements IEnrollStudent {

    IValidateEnrollStudent validateEnrollStudent;
    InputValidator inputValidator;
    IEnrollStudentLogic enrollStudentLogic;
    String userId;
    String courseId;
    String Term;
    Scanner scanner;

    public EnrollStudent(IEnrollStudentLogic enrollStudentLogic, IValidateEnrollStudent validateEnrollStudent) {
        this.inputValidator = new AdminInputValidator();
        this.enrollStudentLogic = enrollStudentLogic;
        this.validateEnrollStudent = validateEnrollStudent;
    }

    public void creation() {
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

    public void SelectMenu() {
        String menuOption = scanner.nextLine();
        ValidateInput(menuOption);
    }

    public void ValidateInput(String input) {
        if (this.inputValidator.validate(input)) {
            String output = validateEnrollStudent.validateInput(userId, courseId, Term);
            final String TO_PROCEED = "true";
            if (output.equals(TO_PROCEED)) {
                enrollStudentLogic.enrollStudent(userId, courseId, Term);
//                dash.showDashboard();
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
