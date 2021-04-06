package com.group9.server.ManageLecture;

import java.util.Date;
import java.util.List;

public interface IManageLectureLogic {
    boolean viewLectures(String facultyId, String courseId);

    boolean scheduleLecture(String facultyId, String courseId, String lecAgenda, String lecDate);

    boolean rescheduleLecture(String lecId, String courseId, String lecAgenda, String lecDate);

    boolean cancelLecture(String courseId, String lecId);

    List<Lecture> getLectures(String facultyId, String courseId);

    boolean validateCourseId(String facultyId, String courseId);

    boolean doesCourseExist(String facultyId, String courseId, Date lecDate);

    boolean validateLectureId(String courseId, String lectureId);

    boolean doesLectureExist(String courseId, Date lecDate);
}
