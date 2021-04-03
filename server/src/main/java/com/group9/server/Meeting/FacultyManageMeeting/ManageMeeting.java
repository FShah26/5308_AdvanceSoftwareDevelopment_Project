package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Dashboard.IDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class ManageMeeting implements IManageMeeting{

    IDashboard dash;
    IManageMeetingLogic meetingLogic;
    String facultyId;
    Scanner sc=new Scanner(System.in);

    @Autowired
    public ManageMeeting(@Qualifier("facultyDashboard") IDashboard dash,IManageMeetingLogic meetingLogic)
    {
        this.dash = dash;
        this.meetingLogic=meetingLogic;
    }

    @Override
    public void display(String facultyId) throws SQLException {
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
    public void selectMenu() throws SQLException {
        String menuOption = sc.nextLine();
        checkInput(menuOption);
    }

    public void manageMeetingAction(String selection) {
        try {
            if (selection.equals("*")) {
                dash.showDashboard();
            }
            else {
                    ArrayList<ManageMeetingDetails> details = meetingLogic.viewMeetings(this.facultyId, selection);
                    if (details.size() > 0) {
                    out.println("________________________________________________________________________________________________________________________________");
                    out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", "MeetingID", "Raised By", "Course", "Requested On", "Status", "Reason");
                    out.println("--------------------------------------------------------------------------------------------------------------------------------");
                    for (ManageMeetingDetails m : details) {
                        out.printf("%-20s%-20s%-20s%-20s%-20s%-20s\n", m.meetingId, m.raisedBy, m.raisedFor, m.raisedOn, m.status, m.reason);
                    }
                    if (selection.equals("1")) {
                        out.println("Do you want to respond to any meeting request? ");
                        out.println("yes");
                        out.println("no");
                        String userinput = sc.nextLine();

                        if (userinput.equals("yes")) {
                            out.println("Enter meeting id for which you want to respond:");
                            String meetId = sc.nextLine();
                            out.println("Enter meeting Response(Approve/Reject): ");
                            String decision = sc.nextLine();
                            out.println("Enter response text:");
                            String response = sc.nextLine();
                            if (meetingLogic.validateinput(meetId, decision) && response.length() > 0) {
                                String output = meetingLogic.respondMeetingRequest(Integer.parseInt(meetId), decision, response);
                                out.println(output);
                            }
                            else {
                                out.println("Incorrect inputs provided..");
                                checkInput(selection);
                            }
                        }
                        else if (userinput.equals("no")) {
                            display(this.facultyId);
                        }
                        else {
                            out.print("Incorrect Input");
                        }
                    }
                }
                else {
                    out.println("Seems like you don't have any request in this state..");
                }
            }
        }
        catch (Exception ex){
            out.println("Error occurred");
        }
    }

    @Override
    public void checkInput(String selection){
        try {
            if (meetingLogic.meetingLogic(selection)) {
                manageMeetingAction(selection);
            }
            else {
                displayInvalidMenuOptionMsg();
                selectMenu();
            }
        }
        catch (Exception ex){
            out.println("System got some error");
        }
    }

    @Override
    public void displayInvalidMenuOptionMsg() {
        out.println("Invalid Option! Please choose a valid option from menu.");
    }
}
