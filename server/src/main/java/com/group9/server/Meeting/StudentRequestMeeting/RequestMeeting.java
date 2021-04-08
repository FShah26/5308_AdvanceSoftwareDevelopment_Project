package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class RequestMeeting implements IRequestMeeting {

    private static final int MAX_LENGTH =45;
    private static final int MIN_LENGTH =0;
    private static final int MIN_COURSES =0;
    private static final int MIN_MEETINGS =0;
    private static final String RAISE_MEETING = "1";
    private static final String VIEW_MEETING_STATUS = "2";
    private static final String GO_BACK = "*";

    IUserInputValidator meetingOptionValidator;
    IRequestMeetingLogic meeting;
    ICourseSelectionValidator courseSelection;
    IRequestMeetingLogic meetingLogic;
    RegisteredCourses course;
    String studentId = "";
    Scanner scanner = new Scanner(System.in);

    public RequestMeeting(IUserInputValidator meetingOptionValidator, IRequestMeetingLogic meeting, ICourseSelectionValidator courseSelection, IRequestMeetingLogic meetingLogic) {
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
        checkInput(menuOption);
    }

    @Override
    public void selectCourse(int select) {
        int courseOption = Integer.parseInt(scanner.nextLine());
        if (this.courseSelection.validate(String.valueOf(courseOption), select)) {
            System.out.println("Please give reason for Meeting :");
            String reason = scanner.nextLine();
            String selected = course.courseId.get(courseOption - 1);
            if (reason.length() > MAX_LENGTH) {
                System.out.println("Shorter reason expected..Therefore meeting request could not be raised.");
                System.out.println("Please select again valid option from the mentioned courses..");
                selectCourse(select);
            } else if (reason.length() == MIN_LENGTH) {
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
        switch(selection)
        {
            case RAISE_MEETING:
                raiseMeeting(this.studentId);
                break;
            case VIEW_MEETING_STATUS:
                viewMeeting(this.studentId);
                break;
            case GO_BACK:
                System.out.println("Back to Dashboard");
                break;
            default:
                System.out.println("Incorrect input provided");
                break;
        }
    }

    @Override
    public void checkInput(String selection) {
        if (this.meetingOptionValidator.validate(selection)) {
            manageMeetingAction(selection);
        } else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }

    @Override
    public void viewMeeting(String Student) {
        ArrayList<MeetingDetails> details = meetingLogic.meeting(Student);
        if (details.size() > MIN_MEETINGS) {
            System.out.println("__________________________________________________________________________________________________________________");
            System.out.printf("%-20s%-15s%-20s%-35s%-50s\n", "MeetingID", "Course", "Status", "Date", "Faculty Response");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            for (MeetingDetails meeting : details) {
                System.out.printf("%-20s%-15s%-20s%-35s%-50s\n", meeting.meetingId, meeting.raisedFor, meeting.status, meeting.raisedOn, meeting.facultyResponse);
            }
        } else {
            System.out.println("Seems like you haven't raised any meeting request.");
        }
        meetingDisplay(Student);
    }

    @Override
    public void raiseMeeting(String Student) {
        course = meeting.viewCourse(Student);
        if (course.courseId.size() == MIN_COURSES) {
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
        meetingDisplay(Student);
    }

    public void displayInvalidMenuOptionMsg() {
        System.out.println("Invalid Option! Please choose a valid option from above menu.");
    }

    @Override
    public void execute(String userRole, String userId) {
        meetingDisplay(userId);
    }
}
