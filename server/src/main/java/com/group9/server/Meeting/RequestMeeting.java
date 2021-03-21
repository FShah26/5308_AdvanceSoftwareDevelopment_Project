package com.group9.server.Meeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class RequestMeeting implements IRequestMeeting {

    @Autowired
    IUserInputValidator meetingOptionValidator;

    @Autowired
    IRequestMeetingLogic meeting;

    @Autowired
    ICourseSelectionValidator courseSelection;

    @Autowired
    IRequestMeetingLogic meetingLogic;

    RegisteredCourses course;
    String studentId ="";

    Scanner sc=new Scanner(System.in);

    @Override
    public void meetingDisplay(String username) throws SQLException {
        studentId = username;
        System.out.println("************************************************");
        System.out.println("                 REQUEST MEETING                ");
        System.out.println("************************************************");

        System.out.println("-->Press 1 to create a new meeting request");
        System.out.println("-->Press 2 to view the status");
        selectMenu();
    }

    @Override
    public void selectMenu() throws SQLException {
        String menuOption = sc.nextLine();
        checkinput(menuOption);
    }

    @Override
    public void selectCourse(int i) throws SQLException {
        int courseOption = Integer.parseInt(sc.nextLine());
            if(this.courseSelection.validate(String.valueOf(courseOption),i))
            {
                System.out.println("Please give reason for Meeting :");
                String reason = sc.nextLine();
                String selected = course.courseId.get(courseOption-1);
                if(reason.length()>45) {
                    System.out.println("Shorter reason expected..Therefore meeting request could not be raised.");
                    System.out.println("Please select valid option from the mentioned courses..");
                    selectCourse(i);
                }
                else if (reason.length()==0){
                    System.out.println("Reason for meeting not given..Therefore meeting request could not be raised.");
                    System.out.println("Please select valid option from the mentioned courses..");
                    selectCourse(i);
                }
                else
                {
                    String output = meetingLogic.raiseMeetingRequest(selected, studentId, reason);
                    System.out.println(output);
                }
            }
            else
            {
                System.out.println("Please select valid option from the above mentioned courses..");
                selectCourse(i);
            }
    }

    @Override
    public void checkinput(String selection) throws SQLException {
        if (this.meetingOptionValidator.validate(selection)) {
            if (selection.equals("1"))
            {
                course = meeting.viewCourses(studentId);
                if (course.courseId.size() == 0)
                {
                    System.out.println("Looks like you not enrolled to any courses.");
                }
                else {
                    System.out.println("Select course for which you need to request meeting.");
                    int i = 0;
                    for (String courseid : course.courseId) {
                        i++;
                        System.out.println( "Press "+i+" for "+courseid);
                    }
                    selectCourse(i);
                }
            }
            else if (selection.equals("2"))
                {
                   ArrayList<MeetingDetails> details = meetingLogic.meetings(studentId);
                   if(details.size()>0){
                       System.out.println("_______________________________________________________________________________");
                       System.out.printf("%-20s%-15s%-20s%-20s\n", "MeetingID","Course","Status","Date");
                       System.out.println("-------------------------------------------------------------------------------");
                       for(MeetingDetails m: details)
                       {
                           System.out.printf("%-20s%-15s%-20s%-20s\n", m.MeetingId,m.RaisedFor,m.Status,m.RaisedOn);
                       }
                   }
                   else
                   {
                       System.out.println("Seems like you haven't raised any meeting request.");
                   }

                }
            else
            {
                System.out.println("Yet to develop...");
            }
        }
        else {
            displayInvalidMenuOptionMsg();
            selectMenu();
        }
    }
    public void displayInvalidMenuOptionMsg(){
        System.out.println("Invalid Option! Please choose a valid option from above menu.");
    }
}
