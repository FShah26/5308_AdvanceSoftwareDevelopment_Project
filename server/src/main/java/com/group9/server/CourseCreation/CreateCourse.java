package com.group9.server.CourseCreation;

import com.group9.server.Dashboard.InputValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class CreateCourse implements ICreateCourse {

    InputValidator inputValidator;
    ICourseLogic courseService;
    String courseId;
    String courseName;
    String courseCredit;
    String courseFaculty;
    String courseDepartment;
    Scanner scanner;

    public CreateCourse(InputValidator inputValidator, ICourseLogic courseService) {
        this.inputValidator = inputValidator;
        this.courseService = courseService;
    }

    @Override
    public void creation() {
        out.println("************************************************");
        out.println("      ENTER DETAILS TO CREATE NEW COURSE        ");
        out.println("************************************************");
        scanner = new Scanner(System.in);
        out.print("Enter Course ID : ");
        courseId = scanner.nextLine();
        out.print("Enter Course Name : ");
        courseName = scanner.nextLine();
        out.print("Enter Course Credit : ");
        courseCredit = scanner.nextLine();
        out.print("Enter Faculty Moderator ID : ");
        courseFaculty = scanner.nextLine();
        out.print("DEPARTMENT : ");
        courseDepartment = scanner.nextLine();

        out.println("-->Press 1 to confirm");
        out.println("-->Press 2 to Cancel");
        selectMenu();

    }

    @Override
    public void selectMenu() {
        String menuOption = scanner.nextLine();
        validateInput(menuOption);
    }

    @Override
    public void validateInput(String input) {
        String message = " ";
        try {
            if (this.inputValidator.validate(input)) {
                message = courseService.courseCreate(courseId, courseName, courseCredit, courseFaculty, courseDepartment);
                System.out.println(message);
            } else {
                displayInvalidMenuOptionMsg();
                creation();
            }
        } catch (Exception ex) {
            System.out.print("Some Unknown Error Occured..");
        }
    }

    @Override
    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from above menu.");
    }

    @Override
    public void execute(String userRole, String userId) {
        creation();
    }
}
