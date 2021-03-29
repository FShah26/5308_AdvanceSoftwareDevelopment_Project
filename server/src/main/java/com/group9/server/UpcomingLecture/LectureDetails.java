package com.group9.server.UpcomingLecture;

public class LectureDetails {
    String facultyid;
    String courseid;
    String topic;
    String date;

    public LectureDetails(String facultyid,String courseid,String topic,String date)
    {
        this.facultyid=facultyid;
        this.courseid=courseid;
        this.topic=topic;
        this.date = date;
    }
}



