package com.group9.server.ManageLecture;

import com.group9.server.Common.IUserConfirmation;
import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class ManageLecture implements IManageLecture {
    private static final String SCHEDULE_LECTURE_PARAMETER = "1";
    private static final String RESCHEDULE_LECTURE_PARAMETER = "2";
    private static final String CANCEL_LECTURE_PARAMETER = "3";
    private static final String NULL_PARAMETER = "*";
    String facultyId;
    IManageLectureLogic manageLectureLogic;
    IUserInputValidator manageLectureOptionValidator;
    IUserConfirmation userConfirmation;
    Map<String, IManageLectureActions> action = new HashMap<>();

    public ManageLecture(IManageLectureLogic manageLectureLogic, IUserConfirmation userConfirmation) {
        this.facultyId = "";
        this.manageLectureLogic = manageLectureLogic;
        this.userConfirmation = userConfirmation;
        this.manageLectureOptionValidator = new ManageLectureOptionValidator();
        action.put(SCHEDULE_LECTURE_PARAMETER, new ScheduleLecture(this.manageLectureLogic));
        action.put(RESCHEDULE_LECTURE_PARAMETER, new RescheduleLecture(this.manageLectureLogic));
        action.put(CANCEL_LECTURE_PARAMETER, new CancelLecture(this.manageLectureLogic));
        action.put(NULL_PARAMETER, null);
    }

    @Override
    public void showManageLectureMenu(String facultyId) {
        this.facultyId = facultyId;
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
        Scanner scanner = new Scanner(System.in);
        String menuOption = scanner.nextLine();
        checkInput(menuOption);
    }

    public void checkInput(String selection) {
        if (this.manageLectureOptionValidator.validate(selection)) {
            manageLectureAction(selection);
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    @Override
    public void manageLectureAction(String selection) {
        IManageLectureActions manageLectureAction = this.action.get(selection.trim());
        if (manageLectureAction != null) {
            manageLectureAction.setFacultyId(this.facultyId);
            manageLectureAction.getUserInputs();
            if (userConfirmation.getUserConfirmation()) {
                if (manageLectureAction.save()) {
                    System.out.println("Operation Successful");
                }
            }
            showManageLectureMenu(this.facultyId);
        }
    }

    public void displayInvalidMenuOptionMsg() {
        System.out.println("Invalid Option! Please choose a valid option from menu.");
    }

    @Override
    public void execute(String userRole, String userId) {
        showManageLectureMenu(userId);
    }
}
