package com.group9.server.UpcomingLecture;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class UpcomingLectureLogic implements IUpcomingLectureLogic {

    ILecturePersistence lecturePersistence;

    public UpcomingLectureLogic(ILecturePersistence lecture) {
        this.lecturePersistence = lecture;
    }

    @Override
    public ArrayList<LectureDetails> upcoming(String studentId) {
        ArrayList<LectureDetails> details = new ArrayList<LectureDetails>();
        try {
            ResultSet set = lecturePersistence.viewLecture(studentId);
            if (set != null) {
                while (set.next()) {
                    String facultyid = set.getString(1);
                    String courseid = set.getString(2);
                    String topic = set.getString(3);
                    String date = set.getString(4);
                    LectureDetails lectureDetails = new LectureDetails(facultyid, courseid, topic, date);
                    details.add(lectureDetails);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return details;
    }
}
