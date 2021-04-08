package com.group9.server.Meeting.StudentRequestMeeting;

public class MeetingDetails {
    String meetingId;
    String raisedFor;
    String raisedOn;
    String status;
    String facultyResponse;

    public MeetingDetails(String meetingId, String raisedFor, String raisedOn, String status, String facultyResponse) {
        this.meetingId = meetingId;
        this.raisedFor = raisedFor;
        this.raisedOn = raisedOn;
        this.status = status;
        this.facultyResponse = facultyResponse;
    }
}


