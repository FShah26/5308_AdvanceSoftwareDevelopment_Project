package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ScheduleLecture {
    public String facultyId;
    public String courseId;
    public String topic;
    public String lecDate;

    IManageLectureLogic manageLectureLogic;
    IUserInputValidator userConfirmationOptionValidator;

    @Autowired
    public ScheduleLecture(IManageLectureLogic manageLectureLogic){
        this.userConfirmationOptionValidator=new UserConfirmationOptionValidator();
        this.manageLectureLogic=manageLectureLogic;
    }

    public void showMenu() {
        String selectedOption;
        System.out.println("************************************************");
        System.out.println("               SCHEDULING LECTURE                ");
        System.out.println("************************************************");
        this.courseId=getCourseId();
        this.topic=getLectureTopic();
        this.lecDate=getLectureDate();
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
        selectedOption=selectMenu();
        confirmAndSave(selectedOption);

    }

    public String selectMenu() {
        Scanner sc=new Scanner(System.in);
        String menuOption = sc.nextLine();
        return menuOption;
    }
    public String getCourseId() {
        System.out.println("Enter the course ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getLectureTopic(){
        System.out.println("Enter the topic for the lecture:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getLectureDate(){
        System.out.println("Enter the date of the lecture (format:dd/MM/yyyy HH:mm:ss) :");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public boolean confirmAndSave(String selection) {
        boolean result=false;
        if (this.userConfirmationOptionValidator.validate(selection)) {
            if (selection.equals("1")) {
                result=manageLectureLogic.scheduleLecture(this.facultyId,this.courseId,this.topic,this.lecDate);
            } else {
                System.out.println("Yet to develop...");
                result=false;
            }
        }
        else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
        return result;
    }
    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
