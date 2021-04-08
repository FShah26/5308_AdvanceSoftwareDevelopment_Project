package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Common.IUserConfirmation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ManageMeeting implements IManageMeeting {

    private static final int SELECTED_OPTION_VALUE = 0;
    private static final int MINIMUM_MEETING_RECORD = 0;
    private static final String SHOW_PENDING_MEETING = "1";
    IManageMeetingLogic manageMeetingLogic;
    IUserConfirmation userConfirmation;
    String facultyId;
    Scanner scanner = new Scanner(System.in);

    public ManageMeeting(IManageMeetingLogic manageMeetingLogic, IUserConfirmation userConfirmation) {
        this.manageMeetingLogic = manageMeetingLogic;
        this.userConfirmation = userConfirmation;
    }

    @Override
    public void meetingDisplay(String facultyId) {
        this.facultyId = facultyId;
        System.out.println("************************************************");
        System.out.println("                 MANAGE MEETING                 ");
        System.out.println("************************************************");

        System.out.println("-->Press 1 for pending meeting request");
        System.out.println("-->Press 2 for approved meeting request");
        System.out.println("-->Press 3 for reject meeting request");
        System.out.println("-->Press * to back to dashboard");
        selectMenu();
    }

    @Override
    public void selectMenu() {
        String menuOption = scanner.nextLine();
        checkInput(menuOption);
    }

    @Override
    public void manageMeetingAction(String selection) {
        try {
            if (Character.isDigit(selection.charAt(SELECTED_OPTION_VALUE))) {
                ArrayList<ManageMeetingDetails> details = manageMeetingLogic.viewMeetings(this.facultyId, selection);
                if (details.size() > MINIMUM_MEETING_RECORD) {
                    System.out.println("________________________________________________________________________________________________________________________________");
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "MeetingID", "Raised By", "Course", "Requested On", "Status", "Reason");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    for (ManageMeetingDetails meeting : details) {
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", meeting.meetingId, meeting.raisedBy, meeting.raisedFor, meeting.raisedOn, meeting.status, meeting.reason);
                    }

                    if (selection.equals(SHOW_PENDING_MEETING)) {
                        takeAction(selection);
                    }
                } else {
                    System.out.println("Seems like you don't have any request in this state..");
                }
            } else {
                System.out.println("Back to Dashboard");
            }
        } catch (Exception ex) {
            System.out.println("Error occurred");
        }
    }

    @Override
    public void takeAction(String selection) {
        System.out.println("Do you want to respond to any meeting request? ");

        if (userConfirmation.getUserConfirmation()) {
            System.out.println("Enter meeting id for which you want to respond:");
            String meetId = scanner.nextLine();
            System.out.println("Enter meeting Response(Approve/Reject): ");
            String decision = scanner.nextLine();
            System.out.println("Enter response text:");
            String response = scanner.nextLine();
            if (manageMeetingLogic.validateInput(meetId, decision) && response.length() > 0) {
                String output = manageMeetingLogic.respondMeetingRequest(Integer.parseInt(meetId), decision, response);
                System.out.println(output);
            } else {
                System.out.println("Incorrect inputs provided..");
                checkInput(selection);
            }
        } else {
            meetingDisplay(this.facultyId);
        }
    }

    @Override
    public void checkInput(String selection) {
        try {
            if (manageMeetingLogic.meetingValidation(selection)) {
                manageMeetingAction(selection);
            } else {
                displayInvalidMenuOptionMsg();
                selectMenu();
            }
        } catch (Exception ex) {
            System.out.println("System got some error");
        }
    }

    @Override
    public void displayInvalidMenuOptionMsg() {
        System.out.println("Invalid Option! Please choose a valid option from menu.");
    }

    @Override
    public void execute(String userRole, String userId) {
        meetingDisplay(userId);
    }
}
