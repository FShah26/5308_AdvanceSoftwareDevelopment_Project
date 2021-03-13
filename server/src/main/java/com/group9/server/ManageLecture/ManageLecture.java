package com.group9.server.ManageLecture;

import com.group9.server.Dashboard.InputValidator;
import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ManageLecture implements IManageLecture{

    String facultyId;
    IManageLectureLogic manageLectureLogic;
    IUserInputValidator manageLectureOptionValidator;

//    @Autowired
//    ScheduleLecture scheduleLecture;


    @Autowired
    public ManageLecture(IManageLectureLogic manageLectureLogic){
        this.facultyId="";
        this.manageLectureLogic=manageLectureLogic;
        this.manageLectureOptionValidator=new ManageLectureOptionValidator();
    }

    @Override
    public void showManageLectureMenu(String facultyId) {
        this.facultyId=facultyId;
        System.out.println("************************************************");
        System.out.println("               MANAGE LECTURES                ");
        System.out.println("************************************************");

        System.out.println("Press 1 --> Schedule a lecture");
        System.out.println("Press 2 --> Reschedule a lecture");
        System.out.println("Press 3 --> Cancel a lecture ");
        System.out.println();
        selectMenu();
    }

    public void selectMenu() {
        Scanner sc = new Scanner(System.in);
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    public void checkinput(String selection) {
        if (this.manageLectureOptionValidator.validate(selection)) {
            if (selection.equals("1")) {
                scheduleLecture();
            } else {
                System.out.println("Yet to develop...");
            }
        }
        else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    @Override
    public void scheduleLecture() {
        ScheduleLecture scheduleLecture=new ScheduleLecture(this.manageLectureLogic);
        scheduleLecture.facultyId=this.facultyId;
        scheduleLecture.showMenu();
    }

    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }


}
