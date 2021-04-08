package com.group9.server.ManageLecture;

import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class ScheduleLecture implements IManageLectureActions {
    public String facultyId;
    public String courseId;
    public String topic;
    public String lecDate;

    IManageLectureLogic manageLectureLogic;

    public ScheduleLecture(IManageLectureLogic manageLectureLogic) {
        this.manageLectureLogic = manageLectureLogic;
    }

    @Override
    public void getUserInputs() {
        System.out.println("************************************************");
        System.out.println("               SCHEDULING LECTURE                ");
        System.out.println("************************************************");
        this.courseId = getCourseId();
        this.topic = getLectureTopic();
        this.lecDate = getLectureDate();
    }


    public String getCourseId() {
        System.out.println("Enter the course ID:");
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

    @Override
    public boolean save() {
        boolean result = false;
        result = manageLectureLogic.scheduleLecture(this.facultyId, this.courseId, this.topic, this.lecDate);
        if (result) {
            System.out.println("Lecture Scheduled successfully");
        } else {
            System.out.println("Lecture scheduling failed.");
        }
        return result;
    }

    @Override
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }
}
