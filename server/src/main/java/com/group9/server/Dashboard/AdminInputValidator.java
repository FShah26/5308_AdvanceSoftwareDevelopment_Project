package com.group9.server.Dashboard;

public class AdminInputValidator implements IAdminInputValidator {

    String regex;

    public AdminInputValidator(){
        this.regex="^\\s*([1-4])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
