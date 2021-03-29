package com.group9.server.AddUser;

import com.group9.server.UserCreation.ValidateAddUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ValidateAddUserTest {

    @Test
    void invalid_input() {
        ValidateAddUser vcc = new ValidateAddUser();
        Assertions.assertEquals("False",
                vcc.validate_input("24","un","u9","Faculty"));
    }

    @Test
    void valid_input() {
        ValidateAddUser vcc = new ValidateAddUser();
        Assertions.assertEquals("true",
                vcc.validate_input("1","unpatel22","utkarshp","2"));
    }
}