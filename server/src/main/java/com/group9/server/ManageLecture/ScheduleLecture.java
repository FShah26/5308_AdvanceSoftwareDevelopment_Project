package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ScheduleLecture implements IManageLectureActions {
    public String facultyId;
    public String courseId;
    public String topic;
    public String lecDate;

    IManageLectureLogic manageLectureLogic;
    IUserInputValidator userConfirmationOptionValidator;

    public ScheduleLecture(IManageLectureLogic manageLectureLogic) {
        this.userConfirmationOptionValidator = new UserConfirmationOptionValidator();
        this.manageLectureLogic = manageLectureLogic;
    }

    @Override
    public void getUserInputs() {
        String selectedOption;
        System.out.println("************************************************");
        System.out.println("               SCHEDULING LECTURE                ");
        System.out.println("************************************************");
        this.courseId = getCourseId();
        this.topic = getLectureTopic();
        this.lecDate = getLectureDate();
    }

    public void showUserConfirmationOptions() {
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
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
            out.println("Lecture Scheduled successfully");
        } else {
            out.println("Lecture scheduling failed.");
        }
        return result;
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
