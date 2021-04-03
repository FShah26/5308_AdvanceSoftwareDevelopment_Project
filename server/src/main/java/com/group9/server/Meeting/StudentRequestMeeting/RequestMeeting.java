package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class RequestMeeting implements IRequestMeeting {

    IUserInputValidator meetingOptionValidator;
    IRequestMeetingLogic meeting;
    ICourseSelectionValidator courseSelection;
    IRequestMeetingLogic meetingLogic;

    RegisteredCourses course;
    String studentId = "";
    Scanner scanner = new Scanner(System.in);

    public RequestMeeting(IUserInputValidator meetingOptionValidator, IRequestMeetingLogic meeting, ICourseSelectionValidator courseSelection, IRequestMeetingLogic meetingLogic, @Qualifier("studentDashboard") IDashboard dash) {
        this.meetingOptionValidator = meetingOptionValidator;
        this.meeting = meeting;
        this.courseSelection = courseSelection;
        this.meetingLogic = meetingLogic;
    }

    @Override
    public void meetingDisplay(String studentId) {
        this.studentId = studentId;
        System.out.println("************************************************");
        System.out.println("                 REQUEST MEETING                ");
        System.out.println("************************************************");

        System.out.println("-->Press 1 to create a new meeting request");
        System.out.println("-->Press 2 to view the status");
        System.out.println("-->Press * to go back to dashboard");
        selectMenu();
    }

    @Override
    public void selectMenu() {
        String menuOption = scanner.nextLine();
        checkinput(menuOption);
    }

    @Override
    public void selectCourse(int select) {
        int courseOption = Integer.parseInt(scanner.nextLine());
        if (this.courseSelection.validate(String.valueOf(courseOption), select)) {
            System.out.println("Please give reason for Meeting :");
            String reason = scanner.nextLine();
            String selected = course.courseId.get(courseOption - 1);
            if (reason.length() > 45) {
                System.out.println("Shorter reason expected..Therefore meeting request could not be raised.");
                System.out.println("Please select again valid option from the mentioned courses..");
                selectCourse(select);
            } else if (reason.length() == 0) {
                System.out.println("Reason for meeting not given..Therefore meeting request could not be raised.");
                System.out.println("Please select again valid option from the mentioned courses..");
                selectCourse(select);
            } else {
                String output = meetingLogic.raiseMeetingRequest(selected, this.studentId, reason);
                System.out.println(output);
                meetingDisplay(this.studentId);
            }
        } else {
            System.out.println("Please select again valid option from the above mentioned courses..");
            selectCourse(select);
        }
    }

    @Override
    public void manageMeetingAction(String selection) {
        try {
            if (selection.equals("1")) {
                course = meeting.viewCourse(this.studentId);
                if (course.courseId.size() == 0) {
                    System.out.println("Looks like you not enrolled to any courses.");
                } else {
                    System.out.println("Select course for which you need to request meeting.");
                    int keyNumber = 0;
                    for (String courseid : course.courseId) {
                        keyNumber++;
                        System.out.println("Press " + keyNumber + " for " + courseid);
                    }
                    selectCourse(keyNumber);
                }
                meetingDisplay(this.studentId);
            } else if (selection.equals("2")) {
                ArrayList<MeetingDetails> details = meetingLogic.meeting(this.studentId);
                if (details.size() > 0) {
                    System.out.println("__________________________________________________________________________________________________________________");
                    System.out.printf("%-20s%-15s%-20s%-35s%-50s\n", "MeetingID", "Course", "Status", "Date", "Faculty Response");
                    System.out.println("------------------------------------------------------------------------------------------------------------------");
                    for (MeetingDetails meeting : details) {
                        System.out.printf("%-20s%-15s%-20s%-35s%-50s\n", meeting.meetingId, meeting.raisedFor, meeting.status, meeting.raisedOn, meeting.facultyResponse);
                    }
                } else {
                    System.out.println("Seems like you haven't raised any meeting request.");
                }
                meetingDisplay(this.studentId);
            } else if (selection.equals("*")) {
                System.out.println("Back to Dashboard");
            }
        } catch (Exception ex) {
            System.out.println("Some error occurred");
        }
    }

    @Override
    public void checkinput(String selection) {
        if (this.meetingOptionValidator.validate(selection)) {
            manageMeetingAction(selection);
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    public void displayInvalidMenuOptionMsg() {
        System.out.println("Invalid Option! Please choose a valid option from above menu.");
    }
}
