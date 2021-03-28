package com.group9.server.CourseCreation;

import com.group9.server.CourseCreation.IValidate;
import org.springframework.stereotype.Component;

@Component
public class ValidateCourseCreation implements IValidate {

    @Override
    public String validate_input(String CourseID,String Credit,String FacultyID) {

        String output="";
        if(CourseID.length()<4)
            output ="PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else if(!(CourseID.substring(0,4)).equals("CSCI"))
            output ="PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else  if(FacultyID.length()<3)
            output ="PLEASE ENTER VALID FACULTY ID";
        else if(Integer.parseInt(Credit)>=5 || Integer.parseInt(Credit)==0)
            output ="PLEASE ENTER VALID CREDIT BETWEEN 1 TO 5";
        else
            output ="true";

        return output;
    }
}
