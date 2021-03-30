package com.group9.server.Meeting.Faculty;

import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Login.IUserInputValidator;
import com.group9.server.Meeting.MeetingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ManageMeetingLogic implements IManageMeetingLogic{

    IUserInputValidator managemeetingvalidate;
    IManageMeetingPersistence meetingPersistence;

    @Autowired
    public ManageMeetingLogic(@Qualifier("manageMeetingOptionValidator") IUserInputValidator managemeetingvalidate, IManageMeetingPersistence meetingPersistence)
    {
        this.managemeetingvalidate = managemeetingvalidate;
        this.meetingPersistence=meetingPersistence;
    }

    @Override
    public Boolean meetingLogic(String selectedOption)
    {
        String output="";
        if (this.managemeetingvalidate.validate(selectedOption)){
            return true;
        }
            return false;
    }

    @Override
    public Boolean validateinput(String selection,String decision)
    {
        if(selection.matches("[0-9]+") && selection.length()>0 && (decision.equals("Approve") || decision.equals("Reject"))) {
            return true;
        }
            return false;
    }

    @Override
    public ArrayList<ManageMeetingDetails> viewMeetings(String facultyId,String selection) {
        ArrayList<ManageMeetingDetails> details = new ArrayList<ManageMeetingDetails>();
        try{
            ResultSet set = meetingPersistence.meetingLog(facultyId,selection);
            while (set.next()) {
                String meetingId = set.getString(1);
                String raisedBy = set.getString(2);
                String raisedFor = set.getString(3);
                String raisedOn = set.getString(4);
                String status = set.getString(5);
                String reason = set.getString(6);
                ManageMeetingDetails meetingDetails = new ManageMeetingDetails(meetingId,raisedBy,raisedFor,raisedOn,status,reason);
                details.add(meetingDetails);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return details;
    }

    @Override
    public String respondMeetingRequest(int meetingId,String decision, String response) {
        String output="";
        try {
            output = meetingPersistence.responseMeeting(meetingId,decision,response);
        }
        catch (Exception ex){
            ex.getMessage();
            output="Error occured.. Unable to raise meeting.";
        }
        return output;
    }
}
