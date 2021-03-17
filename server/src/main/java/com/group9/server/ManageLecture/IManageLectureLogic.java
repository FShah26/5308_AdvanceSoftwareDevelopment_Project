package com.group9.server.ManageLecture;

import java.util.Date;
import java.util.List;

public interface IManageLectureLogic {
    List<Lecture> viewLectures(String courseId);
    boolean scheduleLecture(String facultyId,String courseId, String lecAgenda, String lecDate);
    boolean rescheduleLecture();
    boolean cancelLecture();
}
