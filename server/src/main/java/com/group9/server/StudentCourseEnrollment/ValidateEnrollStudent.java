package com.group9.server.StudentCourseEnrollment;

import com.group9.server.CourseCreation.IValidate;
import com.group9.server.StudentCourseEnrollment.IValidateEnrollStudent;
import org.springframework.stereotype.Component;

@Component
public class ValidateEnrollStudent implements IValidateEnrollStudent {

    @Override
    public String validate_input(String userId,String courseId,String Term) {

        String output="";
        if(!(courseId).startsWith("CSCI"))
            output ="PLEASE ENTER VALID COURSE ID STARTING WITH CSCI";
        else
            output ="true";

        return output;
    }
}
