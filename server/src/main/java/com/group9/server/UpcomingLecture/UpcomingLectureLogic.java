package com.group9.server.UpcomingLecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class UpcomingLectureLogic implements IUpcomingLectureLogic {

    @Autowired
    ILecturePersistence lecture;

    @Autowired
    public UpcomingLectureLogic(ILecturePersistence lecture) {
        this.lecture = lecture;
    }

    @Override
    public ArrayList<LectureDetails> upcoming(String studentId) {
        ArrayList<LectureDetails> details = new ArrayList<LectureDetails>();
        try{
            ResultSet set = lecture.viewLecture(studentId);
            if(set!=null){
                while (set.next()) {
                    String facultyid = set.getString(1);
                    String courseid = set.getString(2);
                    String topic = set.getString(3);
                    String date=set.getString(4);
                    LectureDetails lectureDetails = new LectureDetails(facultyid,courseid,topic,date);
                    details.add(lectureDetails);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return details;
    }
}
