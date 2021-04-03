package com.group9.server.Meeting.FacultyManageMeeting;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ManageMeeting implements IManageMeeting {

    IManageMeetingLogic manageMeetingLogic;
    String facultyId;
    Scanner scanner = new Scanner(System.in);

    public ManageMeeting(IManageMeetingLogic manageMeetingLogic) {
        this.manageMeetingLogic = manageMeetingLogic;
    }

    @Override
    public void display(String facultyId) {
        this.facultyId = facultyId;
        out.println("************************************************");
        out.println("                 MANAGE MEETING                 ");
        out.println("************************************************");

        out.println("-->Press 1 for pending meeting request");
        out.println("-->Press 2 for approved meeting request");
        out.println("-->Press 3 for reject meeting request");
        out.println("-->Press * to back to dashboard");
        selectMenu();
    }

    @Override
    public void selectMenu() {
        String menuOption = scanner.nextLine();
        checkInput(menuOption);
    }

    public void manageMeetingAction(String selection) {
        try {
            if (selection.equals("*")) {
                System.out.println("Back to Dashboard");
            } else {
                ArrayList<ManageMeetingDetails> details = manageMeetingLogic.viewMeetings(this.facultyId, selection);
                if (details.size() > 0) {
                    System.out.println("________________________________________________________________________________________________________________________________");
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "MeetingID", "Raised By", "Course", "Requested On", "Status", "Reason");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    for (ManageMeetingDetails meeting : details) {
                        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", meeting.meetingId, meeting.raisedBy, meeting.raisedFor, meeting.raisedOn, meeting.status, meeting.reason);
                    }
                    if (selection.equals("1")) {
                        System.out.println("Do you want to respond to any meeting request? ");
                        System.out.println("yes");
                        System.out.println("no");
                        String userinput = scanner.nextLine();

                        if (userinput.equals("yes")) {
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
                        } else if (userinput.equals("no")) {
                            display(this.facultyId);
                        } else {
                            System.out.print("Incorrect Input");
                        }
                    }
                } else {
                    System.out.println("Seems like you don't have any request in this state..");
                }
            }
        } catch(Exception ex) {
            System.out.println("Error occurred");
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
            out.println("System got some error");
        }
    }

    @Override
    public void displayInvalidMenuOptionMsg(){
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
