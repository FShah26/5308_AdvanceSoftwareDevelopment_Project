package com.group9.server.Meeting.Faculty;

import java.util.ArrayList;

public interface IManageMeetingLogic {
    Boolean meetingLogic(String selectedOption);
    ArrayList<ManageMeetingDetails> viewMeetings(String facultyId, String selection);
    String respondMeetingRequest(int meetingId,String decision, String response);
    Boolean validateinput(String selection,String decision);
}
