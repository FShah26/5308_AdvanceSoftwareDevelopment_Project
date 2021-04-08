package com.group9.server.ManageLecture;

import java.util.Scanner;

public class CancelLecture implements IManageLectureActions {
    public String facultyId;
    public String courseId;
    public String lecId;
    IManageLectureLogic manageLectureLogic;

    public CancelLecture(IManageLectureLogic manageLectureLogic) {
        this.manageLectureLogic = manageLectureLogic;
    }

    @Override
    public void getUserInputs() {
        System.out.println("************************************************");
        System.out.println("               Cancelling LECTURE                ");
        System.out.println("************************************************");
        this.courseId = getCourseId();
        getCourseLectures(this.facultyId, this.courseId);
        this.lecId = getLectureId();
    }

    public String getCourseId() {
        System.out.println("Enter the course ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getLectureId() {
        System.out.println("Enter the lecture ID you want to cancel:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void getCourseLectures(String facultyId, String courseId) {
        while (manageLectureLogic.viewLectures(facultyId, courseId) == false) {
            courseId = getCourseId();
        }
    }


    @Override
    public boolean save() {
        boolean result;
        result = manageLectureLogic.cancelLecture(this.courseId, this.lecId);
        if (result) {
            System.out.println("Lecture Cancelled successfully");
        } else {
            System.out.println("Lecture Cancelled Failed");
        }
        return result;
    }


    @Override
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
}
