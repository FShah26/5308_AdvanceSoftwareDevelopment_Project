package com.group9.server.ManageLecture;

public interface IDateValidator {
    boolean validate(String userInput);
    boolean ValidateFormat(String userInput);
    boolean ValidateFutureDate(String userInput);
}
