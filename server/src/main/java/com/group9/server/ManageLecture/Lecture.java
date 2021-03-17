package com.group9.server.ManageLecture;

import java.util.Date;

public class Lecture {
    Integer lectureId;
    String courseId;
    String lectureAgenda;
    Date lectureDate;


    public Lecture(Integer lecId,String courseId,String lectureAgenda,Date lecDate)
    {
        this.lectureId=lecId;
        this.courseId=courseId;
        this.lectureDate=lecDate;
        this.lectureAgenda = lectureAgenda;
    }

}
