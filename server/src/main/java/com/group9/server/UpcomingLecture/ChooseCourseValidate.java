package com.group9.server.UpcomingLecture;

import org.springframework.stereotype.Component;

@Component
public class ChooseCourseValidate implements IChooseCourseValidate {

    static final String CHOOSE_COURSE_START = "^\\s*([1-";
    static final String CHOOSE_COURSE_END =  "]|\\*)\\s*$";

    String regex;

    @Override
    public boolean validate(String userInput, int courseLength) {
        if (courseLength > 0) {
            this.regex = CHOOSE_COURSE_START + courseLength + CHOOSE_COURSE_END;
            return userInput.matches(this.regex);
        } else {
            return false;
        }
    }
}
