package com.group9.server.UpcomingLecture;

public class LectureDetails {
    String facultyId;
    String courseId;
    String topic;
    String date;

    public LectureDetails(String facultyId, String courseId, String topic, String date) {
        this.facultyId = facultyId;
        this.courseId = courseId;
        this.topic = topic;
        this.date = date;
    }
}



