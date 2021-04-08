package com.group9.server.AddUser;

import com.group9.server.UserCreation.IValidateAddUser;
import com.group9.server.UserCreation.ValidateAddUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateAddUserTest {

    @Test
    void invalidInputTest() {
        IValidateAddUser validateAddUser = new ValidateAddUser();
        Assertions.assertFalse(validateAddUser.validateInput("24","un","u9","Faculty"));
    }

    @Test
    void validInputTest() {
        IValidateAddUser validateAddUser = new ValidateAddUser();
        Assertions.assertTrue(validateAddUser.validateInput("1","unpatel22","utkarshp","2"));
    }
}