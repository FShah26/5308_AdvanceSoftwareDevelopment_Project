package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;

import java.util.Scanner;

import static java.lang.System.out;

public class CancelLecture implements IManageLectureActions {
    public String facultyId;
    public String courseId;
    public String lecId;
    IManageLectureLogic manageLectureLogic;
    IUserInputValidator userConfirmationOptionValidator;

    public CancelLecture(IManageLectureLogic manageLectureLogic) {
        this.userConfirmationOptionValidator = new UserConfirmationOptionValidator();
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
    public void showUserConfirmationOptions() {
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
    }

    @Override
    public boolean save() {
        boolean result = false;
        result = manageLectureLogic.cancelLecture(this.courseId, this.lecId);
        if (result) {
            out.println("Lecture Cancelled successfully");
        } else {
            out.println("Lecture Cancelled Failed");
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
