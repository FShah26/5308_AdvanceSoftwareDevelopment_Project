package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ManageMeetingLogic implements IManageMeetingLogic {

    IUserInputValidator managemeetingvalidate;
    IManageMeetingPersistence meetingPersistence;

    public ManageMeetingLogic(IManageMeetingPersistence meetingPersistence) {
        this.managemeetingvalidate = new ManageMeetingOptionValidator();
        this.meetingPersistence = meetingPersistence;
    }

    @Override
    public Boolean meetingValidation(String selectedOption) {
        return this.managemeetingvalidate.validate(selectedOption);
    }

    @Override
    public Boolean validateInput(String selection, String decision) {
        return selection.matches("[0-9]+") && selection.length() > 0 && (decision.equals("Approve") || decision.equals("Reject"));
    }

    @Override
    public ArrayList<ManageMeetingDetails> viewMeetings(String facultyId, String selection) {
        ArrayList<ManageMeetingDetails> details = new ArrayList<ManageMeetingDetails>();
        try {
            ResultSet set = meetingPersistence.meetingLog(facultyId, selection);
            while (set.next()) {
                String meetingId = set.getString(1);
                String raisedBy = set.getString(2);
                String raisedFor = set.getString(3);
                String raisedOn = set.getString(4);
                String status = set.getString(5);
                String reason = set.getString(6);
                ManageMeetingDetails meetingDetails = new ManageMeetingDetails(meetingId, raisedBy, raisedFor, raisedOn, status, reason);
                details.add(meetingDetails);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return details;
    }

    @Override
    public String respondMeetingRequest(int meetingId, String decision, String response) {
        String output = "";
        try {
            output = meetingPersistence.responseMeeting(meetingId, decision, response);
        } catch (Exception ex) {
            ex.getMessage();
            output = "Error occured.. Unable to raise meeting.";
        }
        return output;
    }
}
