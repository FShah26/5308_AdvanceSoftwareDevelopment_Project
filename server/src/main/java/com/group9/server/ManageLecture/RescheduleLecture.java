package com.group9.server.ManageLecture;

import java.util.Scanner;

import static java.lang.System.out;

public class RescheduleLecture implements IManageLectureActions {

    public String facultyId;
    public String courseId;
    public String lecId;
    public String topic;
    public String lecDate;
    IManageLectureLogic manageLectureLogic;

    public RescheduleLecture(IManageLectureLogic manageLectureLogic) {
        this.manageLectureLogic = manageLectureLogic;
    }

    public String getCourseId() {
        System.out.println("Enter the course ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getLectureId() {
        System.out.println("Enter the lecture ID you want to reschedule:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getLectureTopic() {
        System.out.println("Enter the topic for the lecture:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getLectureDate() {
        System.out.println("Enter the date of the lecture (format:dd/MM/yyyy HH:mm:ss) :");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void getCourseLectures(String facultyId, String courseId) {
        while (manageLectureLogic.viewLectures(facultyId, courseId) == false) {
            courseId = getCourseId();
        }
    }

    @Override
    public void getUserInputs() {
        System.out.println("************************************************");
        System.out.println("               RESCHEDULING LECTURE                ");
        System.out.println("************************************************");
        this.courseId = getCourseId();
        getCourseLectures(this.facultyId, this.courseId);
        this.lecId = getLectureId();
        this.topic = getLectureTopic();
        this.lecDate = getLectureDate();
    }

    @Override
    public boolean save() {
        boolean result = false;
        result = manageLectureLogic.rescheduleLecture(this.lecId, this.courseId, this.topic, this.lecDate);
        if (result) {
            out.println("Lecture Rescheduled successfully");
        } else {
            out.println("Lecture Rescheduled Failed");
        }
        return result;
    }

    @Override
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;

    }


}
