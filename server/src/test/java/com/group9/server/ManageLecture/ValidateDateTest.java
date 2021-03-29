package com.group9.server.ManageLecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.TestComponent;


@TestComponent
public class ValidateDateTest {
    ValidateDate validator=new ValidateDate();

    @DisplayName("InvalidDateFormatValidation")
    @ParameterizedTest
    @CsvSource({
            "2021/11/20 10:25:00",
    })
    public void ValidateInvalidDateFormatTest(String date){
        Assertions.assertFalse(validator.ValidateFormat(date));
    }

    @DisplayName("ValidDateFormatValidation")
    @ParameterizedTest
    @CsvSource({
            "21/12/2021 10:25:00",
    })
    public void ValidateValidDateFormatTest(String date){
        Assertions.assertTrue(validator.ValidateFormat(date));
    }

    @DisplayName("ValidFutureDateFormatValidation")
    @ParameterizedTest
    @CsvSource({
            "21/12/2221 10:25:00",
    })
    public void ValidateValidFutureDateTest(String date){
        Assertions.assertTrue(validator.ValidateFutureDate(date));
    }

    @DisplayName("InValidFutureDateValidation")
    @ParameterizedTest
    @CsvSource({
            "21/12/2020 10:25:00",
    })
    public void ValidateInValidFutureDateTest(String date){
        Assertions.assertFalse(validator.ValidateFutureDate(date));
    }

}
