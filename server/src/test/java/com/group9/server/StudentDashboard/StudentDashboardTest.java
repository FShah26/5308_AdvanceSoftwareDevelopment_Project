package com.group9.server.StudentDashboard;

import com.group9.server.Announcements.Student.ViewAnnouncements;
import com.group9.server.Dashboard.InputValidator;
import com.group9.server.Dashboard.StudentDashboard;
import com.group9.server.Feedback.IFeedback;
import com.group9.server.Meeting.IRequestMeeting;
import com.group9.server.Notes.IStudentNotes;
import com.group9.server.Notifications.ViewUserNotifications;
import com.group9.server.UpcomingLecture.IUpcomingLectureDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class StudentDashboardTest {
    StudentDashboard underTest;

    InputValidator mockValidator = Mockito.mock(InputValidator.class);
    IStudentNotes mockStudentNotes = Mockito.mock(IStudentNotes.class);
    IFeedback mockFeedback = Mockito.mock(IFeedback.class);
    ViewAnnouncements announcements = Mockito.mock(ViewAnnouncements.class);
    ViewUserNotifications notifications = Mockito.mock(ViewUserNotifications.class);
    IRequestMeeting meeting = Mockito.mock(IRequestMeeting.class);
    IUpcomingLectureDisplay lecture = Mockito.mock(IUpcomingLectureDisplay.class);

    @BeforeEach
    public void setUp() {
        underTest = new StudentDashboard(mockValidator, mockStudentNotes, mockFeedback, announcements, notifications);
        underTest.setUsername("hashik");
    }
}
