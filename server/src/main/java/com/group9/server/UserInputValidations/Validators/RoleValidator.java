package com.group9.server.UserInputValidations.Validators;

import com.group9.server.UserInputValidations.Interface.IUserInputValidator;
import org.springframework.stereotype.Component;

@Component
public class RoleValidator implements IUserInputValidator {
    String regex;

        public RoleValidator(){
        this.regex="^\\s*([1-4]|[*])\\s*$";
    }

    @Override
    public boolean validate(String userInput) {
        return userInput.matches(this.regex);
    }
}
