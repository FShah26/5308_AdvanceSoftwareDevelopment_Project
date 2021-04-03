package com.group9.server.AddUser;

import com.group9.server.UserCreation.ValidateAddUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateAddUserTest {

    @Test
    void invalid_input() {
        ValidateAddUser validateAddUser = new ValidateAddUser();
        Assertions.assertEquals("False",
                validateAddUser.validateInput("24","un","u9","Faculty"));
    }

    @Test
    void valid_input() {
        ValidateAddUser validateAddUser = new ValidateAddUser();
        Assertions.assertEquals("true",
                validateAddUser.validateInput("1","unpatel22","utkarshp","2"));
    }
}