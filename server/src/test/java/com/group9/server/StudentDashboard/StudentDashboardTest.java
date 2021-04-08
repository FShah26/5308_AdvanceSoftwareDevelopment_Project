package com.group9.server.StudentDashboard;

import com.group9.server.Dashboard.InputValidator;
import com.group9.server.Dashboard.StudentDashboard;
import com.group9.server.IExecuteAction;
import com.group9.server.Quiz.Student.QuizAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class StudentDashboardTest {
    StudentDashboard underTest;

    InputValidator mockValidator = Mockito.mock(InputValidator.class);
    IExecuteAction mockStudentNotes = Mockito.mock(IExecuteAction.class);
    IExecuteAction mockFeedback = Mockito.mock(IExecuteAction.class);
    IExecuteAction announcements = Mockito.mock(IExecuteAction.class);
    IExecuteAction notifications = Mockito.mock(IExecuteAction.class);
    IExecuteAction meeting = Mockito.mock(IExecuteAction.class);
    IExecuteAction lecture = Mockito.mock(IExecuteAction.class);
    IExecuteAction mockQuizAssessment = Mockito.mock(QuizAssessment.class);

    @BeforeEach
    public void setUp() {
        underTest = new StudentDashboard(mockValidator, notifications, lecture, announcements, mockStudentNotes, meeting, mockFeedback, mockQuizAssessment);
        underTest.setUsername("hashik");
    }
}
