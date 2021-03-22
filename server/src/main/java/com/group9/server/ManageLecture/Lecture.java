package com.group9.server.ManageLecture;

import java.sql.Timestamp;

public class Lecture {
    Integer lectureId;
    String courseId;
    String lectureAgenda;
    Timestamp lectureDate;


    public Lecture(Integer lecId, String courseId, String lectureAgenda, Timestamp lecDate) {
        this.lectureId = lecId;
        this.courseId = courseId;
        this.lectureDate = lecDate;
        this.lectureAgenda = lectureAgenda;
    }

}
