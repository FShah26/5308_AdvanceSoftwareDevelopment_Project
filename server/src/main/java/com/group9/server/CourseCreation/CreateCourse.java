package com.group9.server.CourseCreation;

import com.group9.server.Common.IUserConfirmation;
import com.group9.server.Dashboard.InputValidator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class CreateCourse implements ICreateCourse {

    InputValidator adminCreateCourseConfirm;
    IUserConfirmation userConfirmation;
    ICourseLogic courseService;
    String courseId;
    String courseName;
    String courseCredit;
    String courseFaculty;
    String courseDepartment;
    Scanner scanner;

    public CreateCourse(InputValidator adminCreateCourseConfirm, ICourseLogic courseService, IUserConfirmation userConfirmation) {
        this.adminCreateCourseConfirm = adminCreateCourseConfirm;
        this.courseService = courseService;
        this.userConfirmation = userConfirmation;
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
        validateInput();
    }

    @Override
    public void validateInput() {
        String message = " ";
        if (userConfirmation.getUserConfirmation()) {
            message = courseService.courseCreate(courseId, courseName, courseCredit, courseFaculty, courseDepartment);
            System.out.println(message);
        } else {
            System.out.println("Navigating back to Dashboard");
        }
    }

    @Override
    public void execute(String userRole, String userId) {
        creation();
    }
}
