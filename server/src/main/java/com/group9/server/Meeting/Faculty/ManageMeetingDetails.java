package com.group9.server.Meeting.Faculty;

public class ManageMeetingDetails {
    String meetingId;
    String raisedBy;
    String raisedFor;
    String raisedOn;
    String status;
    String reason;
    public ManageMeetingDetails(String meetingId,String raisedBy,String raisedFor,String raisedOn,String status,String reason)
    {
        this.meetingId=meetingId;
        this.raisedBy=raisedBy;
        this.raisedFor=raisedFor;
        this.raisedOn=raisedOn;
        this.status = status;
        this.reason=reason;
    }
}

