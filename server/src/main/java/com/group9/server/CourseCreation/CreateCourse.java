package com.group9.server.CourseCreation;

import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class CreateCourse implements ICreateCourse{

    InputValidator inputValidator;

    @Qualifier("adminDashboard")
    @Autowired
    IDashboard dash;

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
    @Override
    public void creation() {
        out.println("************************************************");
        out.println("      ENTER DETAILS TO CREATE NEW COURSE        ");
        out.println("************************************************");
         sc = new Scanner(System.in);
        out.print("Enter Course ID : ");
         course_id = sc.nextLine();
        out.print("Enter Course Name : ");
         course_name = sc.nextLine();
        out.print("Enter Course Credit : ");
         course_credit = sc.nextLine();
        out.print("Enter Faculty Moderator ID : ");
         course_faculty = sc.nextLine();
        out.print("DEPARTMENT : ");
         course_Department = sc.nextLine();

        out.println("-->Press 1 to confirm");
        out.println("-->Press 2 to Cancel");
        SelectMenu();

    }

    @Override
    public void SelectMenu() {
        String menuOption = sc.nextLine();
        ValidateInput(menuOption);
    }

    @Override
    public void ValidateInput(String input) {
        String message= " ";
        try
        {
            if (this.inputValidator.validate(input)) {
                     message = courseService.courseCreate(course_id, course_name, course_credit, course_faculty, course_Department);
                     System.out.println(message);
                     dash.dashboard();
            } else {
                displayInvalidMenuOptionMsg();
                creation();
            }
        }
        catch ( Exception ex)
        {
            System.out.print("Some Unknown Error Occured..");
        }
    }
    @Override
    public void displayInvalidMenuOptionMsg(){
        out.println("Invalid Option! Please choose a valid option from above menu.");
    }

}
