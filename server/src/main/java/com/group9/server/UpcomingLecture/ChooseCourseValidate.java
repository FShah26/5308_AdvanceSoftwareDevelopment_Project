package com.group9.server.UpcomingLecture;

import org.springframework.stereotype.Component;

@Component
public class ChooseCourseValidate implements IChooseCourseValidate {
    String regex;

    @Override
    public boolean validate(String userInput, int courseLength) {
        if (courseLength > 0) {
            this.regex = "^\\s*([1-" + courseLength + "]|\\*)\\s*$";
            return userInput.matches(this.regex);
        } else {
            return false;
        }
    }
}
