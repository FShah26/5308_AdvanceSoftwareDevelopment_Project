package com.group9.server.Meeting.FacultyManageMeeting;

import com.group9.server.Login.IUserInputValidator;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ManageMeetingLogic implements IManageMeetingLogic {

    static final String OPTION_VALIDATE = "[0-9]+";
    static final String APPROVE_VALIDATION = "Approve";
    static final String REJECT_VALIDATION = "Reject";
    static final int MEETING_ID = 1;
    static final int RAISED_BY = 2;
    static final int RAISED_FOR = 3;
    static final int RAISED_ON = 4;
    static final int STATUS = 5;
    static final int REASON = 6;
    IUserInputValidator manageMeetingValidate;
    IManageMeetingPersistence meetingPersistence;

    public ManageMeetingLogic(IManageMeetingPersistence meetingPersistence) {
        this.manageMeetingValidate = new ManageMeetingOptionValidator();
        this.meetingPersistence = meetingPersistence;
    }

    @Override
    public Boolean meetingValidation(String selectedOption) {
        return this.manageMeetingValidate.validate(selectedOption);
    }

    @Override
    public Boolean validateInput(String selection, String decision) {
        return selection.matches(OPTION_VALIDATE) && selection.length() > 0 && (decision.equals(APPROVE_VALIDATION) || decision.equals(REJECT_VALIDATION));
    }

    @Override
    public ArrayList<ManageMeetingDetails> viewMeetings(String facultyId, String selection) {
        ArrayList<ManageMeetingDetails> details = new ArrayList<ManageMeetingDetails>();
        try {
            ResultSet set = meetingPersistence.meetingLog(facultyId, selection);
            while (set.next()) {
                String meetingId = set.getString(MEETING_ID);
                String raisedBy = set.getString(RAISED_BY);
                String raisedFor = set.getString(RAISED_FOR);
                String raisedOn = set.getString(RAISED_ON);
                String status = set.getString(STATUS);
                String reason = set.getString(REASON);
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
