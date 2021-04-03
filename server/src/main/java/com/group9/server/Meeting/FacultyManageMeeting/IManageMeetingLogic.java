package com.group9.server.Meeting.FacultyManageMeeting;

import java.util.ArrayList;

public interface IManageMeetingLogic {
    Boolean meetingValidation(String selectedOption);

    ArrayList<ManageMeetingDetails> viewMeetings(String facultyId, String selection);

    String respondMeetingRequest(int meetingId, String decision, String response);

    Boolean validateInput(String selection, String decision);
}
