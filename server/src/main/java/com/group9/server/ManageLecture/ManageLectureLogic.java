package com.group9.server.ManageLecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class ManageLectureLogic implements IManageLectureLogic {

    @Autowired
    IManageLecturePersistence manageLecturePersistence;

    @Autowired
    IDateValidator dateValidator;


    @Autowired
    public ManageLectureLogic(IManageLecturePersistence manageLecturePersistence) {
        this.manageLecturePersistence = manageLecturePersistence;
        this.dateValidator = new ValidateDate();
    }

    @Override
    public boolean viewLectures(String facultyId, String courseId) {
        List<Lecture> lstLectures = getLectures(facultyId, courseId);
        if (lstLectures == null) {
            System.out.println("No lectures found for the entered courseId");
            return false;
        } else {
            System.out.println("************************************************");
            System.out.println("               SCHEDULED LECTURES               ");
            System.out.println("************************************************");

            for (Lecture lec : lstLectures) {
                System.out.println("LectureId : " + lec.lectureId.toString());
                System.out.println("Topic : " + lec.lectureAgenda);
                System.out.println("Date : " + lec.lectureDate.toString());
                System.out.println();
            }
            return true;
        }


    }

    @Override
    public List<Lecture> getLectures(String facultyId, String courseId) {
        ArrayList<Lecture> lstCourseLectures = null;
        try {
            if (validateCourseId(facultyId, courseId)) {
                ResultSet set = manageLecturePersistence.getCourseLectures(courseId);
                if (set == null) {
                    lstCourseLectures = null;
                } else {
                    lstCourseLectures = getListFromResultSet(set);
                }
            }

            return lstCourseLectures;
        } catch (SQLException ex) {
            System.out.println("Error fetching lectures for the course");
            ex.getMessage();
            return lstCourseLectures;
        }
    }

    @Override
    public boolean scheduleLecture(String facultyId, String courseId, String topic, String lecDate) {
        boolean result = false;
        try {
            if (validateCourseId(facultyId, courseId) && dateValidator.validate(lecDate)) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    Date lectureDt = df.parse(lecDate);
                    if (doesCourseExist(facultyId, courseId, lectureDt)) {
                        System.out.println("Lecture is already scheduled");
                        result = false;
                    } else {
                        result = manageLecturePersistence.addLecture(facultyId, courseId, topic, lectureDt);
                    }
                } catch (ParseException ex) {
                    System.out.println("Scheduling lecture failed");
                    ex.getMessage();
                    result = false;
                }
            } else {
                result = false;
            }
        } catch (SQLException exception) {
            System.out.println("Scheduling lecture failed");
            exception.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean rescheduleLecture(String lecId, String courseId, String topic, String lecDate) {
        boolean result;
        try {
            if (validateLectureId(courseId, lecId) && dateValidator.validate(lecDate)) {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    Date lectureDt = df.parse(lecDate);
                    if (doesLectureExist(courseId, lectureDt)) {
                        System.out.println("Lecture is already scheduled");
                        result = false;
                    } else {
                        result = manageLecturePersistence.updateLecture(lecId, topic, lectureDt);
                    }
                } catch (ParseException ex) {
                    System.out.println("Updating lecture failed");
                    ex.getMessage();
                    result = false;
                }
            } else {
                result = false;
            }
        } catch (SQLException exception) {
            System.out.println("Updating lecture failed");
            exception.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public boolean cancelLecture(String courseId, String lecId) {
        boolean result;
        try {
            if (validateLectureId(courseId, lecId)) {
                result = manageLecturePersistence.deleteLecture(lecId);
            } else {
                result = false;
            }
        } catch (SQLException exception) {
            System.out.println("Cancel lecture failed");
            exception.printStackTrace();
            result = false;
        }
        return result;
    }


    public boolean validateCourseId(String facultyId, String courseId) {
        ArrayList<String> lstFacultyCourse = new ArrayList<String>();
        try {
            ResultSet set = manageLecturePersistence.getFacultyCourses(facultyId);

            if (set != null) {
                while (set.next()) {
                    lstFacultyCourse.add(set.getString(1));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error validating courseId");
            ex.getMessage();
            return false;
        }
        if (lstFacultyCourse.contains(courseId)) {
            return true;
        }
        System.out.println("Incorrect Course Id");
        return false;
    }

    public boolean doesCourseExist(String facultyId, String courseId, Date lecDate) {
        boolean result = false;
        ArrayList<Lecture> lstFacultyLectures = new ArrayList<Lecture>();
        try {
            ResultSet set = manageLecturePersistence.getAllLectures(facultyId);
            if (set == null) {
                result = false;
            } else {
                lstFacultyLectures = getListFromResultSet(set);
                result = lstFacultyLectures.stream().anyMatch(x -> x.courseId == courseId && x.lectureDate == lecDate);
            }
        } catch (SQLException ex) {
            System.out.println("Error validating if course exists");
            result = true;
        }
        return result;
    }

    public boolean validateLectureId(String courseId, String lectureId) {
        boolean result = false;
        ArrayList<Lecture> lstCourseLectures = new ArrayList<>();
        try {
            ResultSet set = manageLecturePersistence.getCourseLectures(courseId);

            if (set != null) {
                lstCourseLectures = getListFromResultSet(set);
                result = lstCourseLectures.stream().anyMatch(x -> x.lectureId == parseInt(lectureId));
            }

        } catch (SQLException ex) {
            System.out.println("Error validating lectureId");
            ex.getMessage();
            result = false;
        }
        if (result == false) {
            System.out.println("Incorrect Lecture Id");
            result = false;
        }

        return result;
    }

    public boolean doesLectureExist(String courseId, Date lecDate) {
        boolean result = false;
        ArrayList<Lecture> lstCourseLectures = new ArrayList<>();
        try {
            ResultSet set = manageLecturePersistence.getCourseLectures(courseId);
            if (set == null) {
                result = false;
            } else {
                lstCourseLectures = getListFromResultSet(set);
                result = lstCourseLectures.stream().anyMatch(x -> x.courseId.equals(courseId) && x.lectureDate == lecDate);
            }
        } catch (SQLException ex) {
            System.out.println("Error validating if lecture exists");
            result = true;
        }
        return result;
    }


    private ArrayList<Lecture> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<Lecture> lstLectures = new ArrayList<>();
        while (set.next()) {
            Integer lecId = set.getInt(1);
            String course = set.getString(3);
            String topic = set.getString(4);
            Timestamp date = set.getTimestamp(5);
            Lecture lec = new Lecture(lecId, course, topic, date);
            lstLectures.add(lec);
        }
        return lstLectures;
    }


}
