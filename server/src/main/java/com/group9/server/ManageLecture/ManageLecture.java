package com.group9.server.ManageLecture;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ManageLecture implements IManageLecture{

    String facultyId;
    IManageLectureLogic manageLectureLogic;
    IUserInputValidator manageLectureOptionValidator;
    Map<String,IManageLectureActions> action = new HashMap<>();

    @Autowired
    public ManageLecture(IManageLectureLogic manageLectureLogic){
        this.facultyId="";
        this.manageLectureLogic=manageLectureLogic;
        this.manageLectureOptionValidator=new ManageLectureOptionValidator();
        action.put("1",new ScheduleLecture(this.manageLectureLogic));
        action.put("2", new RescheduleLecture(this.manageLectureLogic));
        action.put("3", new CancelLecture(this.manageLectureLogic));
        action.put("*",null);

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
        System.out.println("Press * --> Go to main menu ");
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
            manageLectureAction(selection);
        }
        else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    @Override
    public void manageLectureAction(String selection){
        IManageLectureActions manageLectureAction = this.action.get(selection.trim());
        if (manageLectureAction != null) {
            manageLectureAction.setFacultyId(this.facultyId);
            manageLectureAction.getUserInputs();
            if (manageLectureAction.getUserConfirmation()) {
                if (manageLectureAction.save()) {
                    out.println("Operation Successful");
                }
            }
            showManageLectureMenu(this.facultyId);
        }

    }



    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }


}
