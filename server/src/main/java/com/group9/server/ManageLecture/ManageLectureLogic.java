package com.group9.server.ManageLecture;

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
    private static final String DATE_FORMAT= "dd/MM/yyyy HH:mm:ss";
    private static final int COLUMN_INDEX_1 = 1;
    private static final int COLUMN_INDEX_3 = 3;
    private static final int COLUMN_INDEX_4 = 4;
    private static final int COLUMN_INDEX_5 = 5;
    IManageLecturePersistence manageLecturePersistence;

    IDateValidator dateValidator;


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
        boolean result;
        try {
            if (validateCourseId(facultyId, courseId) && dateValidator.validate(lecDate)) {
                SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
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
                SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
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
        ArrayList<String> listFacultyCourse = new ArrayList<String>();
        try {
            ResultSet set = manageLecturePersistence.getFacultyCourses(facultyId);

            if (set != null) {
                while (set.next()) {
                    listFacultyCourse.add(set.getString(COLUMN_INDEX_1));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error validating courseId");
            ex.getMessage();
            return false;
        }
        if (listFacultyCourse.contains(courseId)) {
            return true;
        }
        System.out.println("Incorrect Course Id");
        return false;
    }

    public boolean doesCourseExist(String facultyId, String courseId, Date lecDate) {
        boolean result;
        ArrayList<Lecture> listFacultyLectures = new ArrayList<Lecture>();
        try {
            ResultSet set = manageLecturePersistence.getAllLectures(facultyId);
            if (set == null) {
                result = false;
            } else {
                listFacultyLectures = getListFromResultSet(set);
                result = listFacultyLectures.stream().anyMatch(x -> x.courseId == courseId && x.lectureDate == lecDate);
            }
        } catch (SQLException ex) {
            System.out.println("Error validating if course exists");
            result = true;
        }
        return result;
    }

    public boolean validateLectureId(String courseId, String lectureId) {
        boolean result = false;
        ArrayList<Lecture> listCourseLectures = new ArrayList<>();
        try {
            ResultSet set = manageLecturePersistence.getCourseLectures(courseId);

            if (set != null) {
                listCourseLectures = getListFromResultSet(set);
                result = listCourseLectures.stream().anyMatch(x -> x.lectureId == parseInt(lectureId));
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
        ArrayList<Lecture> listCourseLectures = new ArrayList<>();
        try {
            ResultSet set = manageLecturePersistence.getCourseLectures(courseId);
            if (set == null) {
                result = false;
            } else {
                listCourseLectures = getListFromResultSet(set);
                result = listCourseLectures.stream().anyMatch(x -> x.courseId.equals(courseId) && x.lectureDate == lecDate);
            }
        } catch (SQLException ex) {
            System.out.println("Error validating if lecture exists");
            result = true;
        }
        return result;
    }


    private ArrayList<Lecture> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<Lecture> listLectures = new ArrayList<>();
        while (set.next()) {
            Integer lecId = set.getInt(COLUMN_INDEX_1);
            String course = set.getString(COLUMN_INDEX_3);
            String topic = set.getString(COLUMN_INDEX_4);
            Timestamp date = set.getTimestamp(COLUMN_INDEX_5);
            Lecture lec = new Lecture(lecId, course, topic, date);
            listLectures.add(lec);
        }
        return listLectures;
    }


}
