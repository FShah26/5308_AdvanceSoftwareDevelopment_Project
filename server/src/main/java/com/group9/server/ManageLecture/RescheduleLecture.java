package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

import static java.lang.System.out;

public class RescheduleLecture implements IManageLectureActions {

    public String facultyId;
    public String courseId;
    public String lecId;
    public String topic;
    public String lecDate;
    IManageLectureLogic manageLectureLogic;
    IUserInputValidator userConfirmationOptionValidator;

    @Autowired
    public RescheduleLecture(IManageLectureLogic manageLectureLogic) {
        this.userConfirmationOptionValidator = new UserConfirmationOptionValidator();
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
        String selectedOption;
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
    public void showUserConfirmationOptions() {
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
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
    public boolean getUserConfirmation() {
        showUserConfirmationOptions();
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        while (this.userConfirmationOptionValidator.validate(menuOption) == false) {
            displayInvalidMenuOptionMsg();
            showUserConfirmationOptions();
            menuOption = sc.nextLine();
        }
        return (Integer.parseInt(menuOption.trim()) == 1);
    }

    @Override
    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }

    @Override
    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;

    }


}
