package com.group9.server.UpcomingLecture;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class UpcomingLectureLogic implements IUpcomingLectureLogic {

    ILecturePersistence lecturePersistence;
    private static final int LEC_PARAMETER_INDEX_1 = 1;
    private static final int LEC_PARAMETER_INDEX_2 = 2;
    private static final int LEC_PARAMETER_INDEX_3 = 3;
    private static final int LEC_PARAMETER_INDEX_4 = 4;

    public UpcomingLectureLogic(ILecturePersistence lecture) {
        this.lecturePersistence = lecture;
    }

    @Override
    public ArrayList<LectureDetails> upcoming(String studentId) {
        ArrayList<LectureDetails> details = new ArrayList<>();
        try {
            ResultSet set = lecturePersistence.viewLecture(studentId);
            if (set != null) {
                while (set.next()) {
                    String facultyId = set.getString(LEC_PARAMETER_INDEX_1);
                    String courseId = set.getString(LEC_PARAMETER_INDEX_2);
                    String topic = set.getString(LEC_PARAMETER_INDEX_3);
                    String date = set.getString(LEC_PARAMETER_INDEX_4);
                    LectureDetails lectureDetails = new LectureDetails(facultyId, courseId, topic, date);
                    details.add(lectureDetails);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return details;
    }
}
