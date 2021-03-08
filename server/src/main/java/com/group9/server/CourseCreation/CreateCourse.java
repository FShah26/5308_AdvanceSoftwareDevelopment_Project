package com.group9.server.CourseCreation;

import com.group9.server.Dashboard.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class CreateCourse {

    @Autowired
    IValidate validate;

    InputValidator inputValidator;


    public CreateCourse() {
        this.inputValidator = new AdminCreateCourseConfirm();
    }

    @Autowired
    ICourseLogic courseService;
    String course_id;
    String course_name;
    String course_credit;
    String course_faculty;
    String course_Department;

    Scanner sc;
    public void creation() {
        System.out.println("************************************************");
        System.out.println("      ENTER DETAILS TO CREATE NEW COURSE        ");
        System.out.println("************************************************");
         sc = new Scanner(System.in);
        System.out.print("Enter Course ID : ");
         course_id = sc.nextLine();
        System.out.print("Enter Course Name : ");
         course_name = sc.nextLine();
        System.out.print("Enter Course Credit : ");
         course_credit = sc.nextLine();
        System.out.print("Enter Faculty Moderator ID : ");
         course_faculty = sc.nextLine();
        System.out.print("DEPARTMENT : ");
         course_Department = sc.nextLine();

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
            String output = validate.validate_input(course_id, course_credit, course_faculty);

            if (output.equals("true")) {
                courseService.courseCreate(course_id, course_name, course_credit, course_faculty, course_Department);
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
