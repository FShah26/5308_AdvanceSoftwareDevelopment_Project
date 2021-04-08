package com.group9.server.CourseCreation;

import com.group9.server.Common.IUserConfirmation;
import com.group9.server.Dashboard.IInputValidator;
import org.springframework.stereotype.Component;
import java.util.Scanner;


@Component
public class CreateCourse implements ICreateCourse {

    IInputValidator adminCreateCourseConfirm;
    IUserConfirmation userConfirmation;
    ICourseLogic courseService;
    String courseId;
    String courseName;
    String courseCredit;
    String courseFaculty;
    String courseDepartment;
    Scanner scanner;

    public CreateCourse(IInputValidator adminCreateCourseConfirm, ICourseLogic courseService, IUserConfirmation userConfirmation) {
        this.adminCreateCourseConfirm = adminCreateCourseConfirm;
        this.courseService = courseService;
        this.userConfirmation = userConfirmation;
    }

    @Override
    public void creation() {
        System.out.println("************************************************");
        System.out.println("      ENTER DETAILS TO CREATE NEW COURSE        ");
        System.out.println("************************************************");
        scanner = new Scanner(System.in);
        System.out.print("Enter Course ID : ");
        courseId = scanner.nextLine();
        System.out.print("Enter Course Name : ");
        courseName = scanner.nextLine();
        System.out.print("Enter Course Credit : ");
        courseCredit = scanner.nextLine();
        System.out.print("Enter Faculty Moderator ID : ");
        courseFaculty = scanner.nextLine();
        System.out.print("DEPARTMENT : ");
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
