package com.group9.server.Course_Creation;

import com.group9.server.Course_Creation.ServiceLayer.ICourseService;
import com.group9.server.UserInputValidations.Interface.IValidate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class CreateCourse{

    @Autowired
    IValidate validate;

    @Autowired
    ICourseService cs;

    public void creation(){
        System.out.println("************************************************");
        System.out.println("      ENTER DETAILS TO CREATE NEW COURSE        ");
        System.out.println("************************************************");
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter Course ID : ");
        String course_id = sc.nextLine();
        System.out.print("Enter Course Name : ");
        String course_name = sc.nextLine();
        System.out.print("Enter Course Credit : ");
        String course_credit= sc.nextLine();
        System.out.print("Enter Faculty Moderator ID : ");
        String course_faculty =sc.nextLine();
        System.out.print("DEPARTMENT : ");
        String course_Department = sc.nextLine();

        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
        int input = sc.nextInt();

        switch (input){
            case 1:
            {
                String output = validate.validate_input(course_id,course_credit,course_faculty);

                if(output.equals("true")) {
                    cs.Create_Courses(course_id,course_name,course_credit,course_faculty,course_Department);
                }
                else
                    System.out.println(output);

                break;
            }
            case 2:
            {
                System.out.println("COURSE CREATION HAS BEEN CANCELLED");
                break;
            }
            default:
            {
                System.out.println("PLEASE PROVIDE CORRECT INPUT..");
                break;
            }
        }

    }

}
