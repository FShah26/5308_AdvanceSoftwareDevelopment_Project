package com.group9.server.Meeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class CourseSelectionValidator implements ICourseSelectionValidator {
    String regex;

    @Override
    public boolean validate(String userInput, int courselength) {
        this.regex = "^\\s*([1-"+courselength+"])\\s*$";
        return userInput.matches(this.regex);
    }
}
