package com.group9.server.StudentDashboard;

import com.group9.server.Dashboard.InputValidator;
import com.group9.server.Dashboard.StudentDashboard;
import com.group9.server.Notes.IStudentNotes;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class StudentDashboardTest {
    StudentDashboard underTest;

    InputValidator mockValidator = Mockito.mock(InputValidator.class);
    IStudentNotes mockStudentNotes = Mockito.mock(IStudentNotes.class);


    @BeforeEach
    public void setUp() {
        underTest = new StudentDashboard(mockValidator, mockStudentNotes);
        underTest.setUsername("hashik");
    }
}
