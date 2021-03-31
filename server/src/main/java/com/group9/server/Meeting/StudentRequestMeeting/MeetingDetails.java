package com.group9.server.Meeting.StudentRequestMeeting;

public class MeetingDetails {
    String MeetingId;
    String RaisedFor;
    String RaisedOn;
    String Status;
    String facultyResponse;

    public MeetingDetails(String MeetingId,String RaisedFor,String RaisedOn,String Status,String facultyResponse)
    {
        this.MeetingId=MeetingId;
        this.RaisedFor=RaisedFor;
        this.RaisedOn=RaisedOn;
        this.Status = Status;
        this.facultyResponse=facultyResponse;
    }
}


