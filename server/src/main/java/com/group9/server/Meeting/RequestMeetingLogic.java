package com.group9.server.Meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class RequestMeetingLogic implements IRequestMeetingLogic {

    @Autowired
    IRequestMeetingPersistence request;


    @Autowired
    public RequestMeetingLogic(IRequestMeetingPersistence request) {
        this.request = request;
    }

    @Override
    public RegisteredCourses viewCourses(String studentId) {
        RegisteredCourses courses = new RegisteredCourses();
        try {
            ResultSet set = request.fetchRegisteredCourses(studentId);
            if (set != null) {
                while (set.next()) {
                    courses.courseId.add(set.getString(1));
                }
            }
        }
        catch (Exception ex){
            ex.getMessage();
            System.out.println("Unable to fetch course due to some error..");
        }
        return courses;
    }

    @Override
    public String raiseMeetingRequest(String courseId,String studentId,String reason) {
        String response="";
        try {
             response = request.setMeeting(courseId, studentId,reason);
        }
        catch (Exception ex){
            ex.getMessage();
            System.out.println("Error occured.. Unable to raise meeting.");
        }
        return response;
    }

    @Override
    public ArrayList<MeetingDetails> meetings(String studentId) {
        ArrayList<MeetingDetails> details = new ArrayList<MeetingDetails>();
        try{
            ResultSet set = request.viewMeeting(studentId);
                while (set.next()) {
                    String MeetingId=set.getString(1);
                    String RaisedFor = set.getString(2);
                    String RaisedOn = set.getString(3);
                    String Status=set.getString(4);
                    MeetingDetails meetingDetails = new MeetingDetails(MeetingId,RaisedFor,RaisedOn,Status);
                    details.add(meetingDetails);
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return details;
    }
}