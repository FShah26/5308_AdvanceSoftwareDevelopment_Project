package com.group9.server.Meeting;

import java.util.Date;

public class MeetingDetails {
    String MeetingId;
    String RaisedFor;
    String RaisedOn;
    String Status;

    public MeetingDetails(String MeetingId,String RaisedFor,String RaisedOn,String Status)
    {
        this.MeetingId=MeetingId;
        this.RaisedFor=RaisedFor;
        this.RaisedOn=RaisedOn;
        this.Status = Status;
    }
}


