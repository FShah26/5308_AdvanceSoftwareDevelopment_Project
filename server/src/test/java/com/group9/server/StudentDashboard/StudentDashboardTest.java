package com.group9.server.StudentDashboard;

import com.group9.server.Announcements.Student.IViewAnnouncements;
import com.group9.server.Dashboard.InputValidator;
import com.group9.server.Dashboard.StudentDashboard;
import com.group9.server.Database.ISingletonDatabase;
import com.group9.server.Feedback.IFeedback;
import com.group9.server.Meeting.StudentRequestMeeting.IRequestMeeting;
import com.group9.server.Notes.IStudentNotes;
import com.group9.server.Notifications.IViewUserNotifications;
import com.group9.server.UpcomingLecture.IUpcomingLectureDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class StudentDashboardTest {
    StudentDashboard underTest;

    InputValidator mockValidator = Mockito.mock(InputValidator.class);
    IStudentNotes mockStudentNotes = Mockito.mock(IStudentNotes.class);
    IFeedback mockFeedback = Mockito.mock(IFeedback.class);
    IViewAnnouncements announcements = Mockito.mock(IViewAnnouncements.class);
    IViewUserNotifications notifications = Mockito.mock(IViewUserNotifications.class);
    IRequestMeeting meeting = Mockito.mock(IRequestMeeting.class);
    IUpcomingLectureDisplay lecture = Mockito.mock(IUpcomingLectureDisplay.class);
    ISingletonDatabase mockDatabase = Mockito.mock(ISingletonDatabase.class);

    @BeforeEach
    public void setUp() {
        underTest = new StudentDashboard(mockValidator, mockStudentNotes, mockFeedback, announcements, notifications, mockDatabase);
        underTest.setUsername("hashik");
    }
}
