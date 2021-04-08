package com.group9.server.ManageLecture;


import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ValidateDate implements IDateValidator {
    private static final String REGEX_DATE = "^\\s*([0-3]\\d\\/[01]\\d\\/\\d{4}\\s[0-2]\\d\\:[0-5]\\d\\:[0-5]\\d)\\s*$";
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    String regex;

    public ValidateDate() {
        this.regex = REGEX_DATE;
    }

    @Override
    public boolean validate(String userInput) {
        return (ValidateFormat(userInput) && ValidateFutureDate(userInput));
    }

    @Override
    public boolean ValidateFormat(String userInput) {
        if (userInput.matches(this.regex)) {
            return true;
        }
        System.out.println("Date entered in incorrect format!");
        return false;
    }

    @Override
    public boolean ValidateFutureDate(String userInput) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        try {
            Date dt = df.parse(userInput);
            if (dt.after(new Date())) {
                return true;
            }
            System.out.println("Past date entered!");
            return false;

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
