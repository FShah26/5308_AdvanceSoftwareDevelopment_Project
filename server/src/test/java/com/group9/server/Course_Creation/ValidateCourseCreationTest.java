package com.group9.server.Course_Creation;

import com.group9.server.CourseCreation.IValidate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

class ValidateCourseCreationTest {
    IValidate mock = Mockito.mock(IValidate.class);

    @BeforeEach
    public void setUp() throws SQLException {
        when(mock.validateInput("CSCI213", "4", "FID12")).thenReturn(false);
        when(mock.validateInput("CSCI123", "4", "FID12")).thenReturn(true);

    }

    @ParameterizedTest
    @CsvSource({
            "true,CSCI123,4,FID12",
            "false,CSCI213,4,FID12",
    })
    @DisplayName("validateTest")
    void validInputTest(Boolean result, String courseId, String credit, String facultyId) {
        Assertions.assertEquals(result, mock.validateInput(courseId, credit, facultyId));
    }
}