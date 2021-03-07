package com.group9.server.Login;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator implements IUserInputValidator {
    String regex;

    public RoleValidator() {
        this.regex = "^\\s*([1-3]|[*])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
