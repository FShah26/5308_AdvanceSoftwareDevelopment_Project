package com.group9.server.Meeting.StudentRequestMeeting;

import java.util.ArrayList;

public interface IRequestMeetingLogic {
    RegisteredCourses viewCourse(String studentId);

    String raiseMeetingRequest(String courseId, String studentId, String reason);

    ArrayList<MeetingDetails> meeting(String studentId);
}
