package com.group9.server.ManageLecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class ManageLectureLogicTest {
    IManageLecturePersistence mockPersistence = Mockito.mock(IManageLecturePersistence.class);
    IManageLectureLogic underTest;

    private ResultSet mockStringResultSet(ArrayList<String> rows) throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        OngoingStubbing<Boolean> rsNextStub = when(rs.next());
        for (String s : rows) {
            rsNextStub = rsNextStub.thenReturn(true);
        }
        rsNextStub.thenReturn(false);

        OngoingStubbing<String> rsGetStringStub = when(rs.getString(1));
        for (String s : rows) {
            rsGetStringStub = rsGetStringStub.thenReturn(s);
        }

        return rs;
    }

    private ResultSet mockLectureResultSet(ArrayList<Lecture> rows) throws SQLException {
        ResultSet rs = Mockito.mock(ResultSet.class);
        OngoingStubbing<Boolean> rsNextStub = when(rs.next());
        for (Lecture l : rows) {
            rsNextStub = rsNextStub.thenReturn(true);
        }
        rsNextStub.thenReturn(false);

        OngoingStubbing<Integer> rsGetIntegerStub = when(rs.getInt(1));
        for (Lecture l : rows) {
            rsGetIntegerStub = rsGetIntegerStub.thenReturn(l.lectureId);
        }

        OngoingStubbing<String> rsGetStringStub = when(rs.getString(3));
        for (Lecture l : rows) {
            rsGetStringStub = rsGetStringStub.thenReturn(l.courseId);
        }
        OngoingStubbing<String> rsGetTopicStringStub = when(rs.getString(4));
        for (Lecture l : rows) {
            rsGetTopicStringStub = rsGetTopicStringStub.thenReturn(l.lectureAgenda);
        }

        OngoingStubbing<Timestamp> rsGetTimestampStringStub = when(rs.getTimestamp(5));
        for (Lecture l : rows) {
            rsGetTimestampStringStub = rsGetTimestampStringStub.thenReturn(l.lectureDate);
        }
        return rs;
    }

    @BeforeEach
    public void setup() throws SQLException, ParseException {

        underTest = new ManageLectureLogic(mockPersistence);
        ArrayList<String> lstCourses = new ArrayList<>();
        lstCourses.add("CSCI222");
        lstCourses.add("CSCI22");
        ResultSet rsCourses = mockStringResultSet(lstCourses);

        ArrayList<Lecture> lstLectures = new ArrayList<>();
        Date date = new Date("10/05/2021 15:00:00");
        Lecture lec;
        lec = new Lecture(1, "CSCI222", "Test", new Timestamp(date.getTime()));
        lstLectures.add(lec);
        date = new Date("11/05/2021 15:00:00");
        lec = new Lecture(2, "CSCI222", "Test1", new Timestamp(date.getTime()));
        lstLectures.add(lec);
        ResultSet rsLectures = mockLectureResultSet(lstLectures);

        when(mockPersistence.getFacultyCourses("utkarshp123")).thenReturn(rsCourses);
        when(mockPersistence.getAllLectures("utkarshp123")).thenReturn(rsLectures);
        when(mockPersistence.getCourseLectures("CSCI222")).thenReturn(rsLectures);
        doReturn(true).when(mockPersistence).addLecture("utkarshp123", "CSCI222", "testCourse", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("20/02/2050 10:00:00"));
        doReturn(true).when(mockPersistence).updateLecture("1", "testCourse", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("20/02/2050 10:00:00"));
        doReturn(true).when(mockPersistence).deleteLecture("1");


    }

    @ParameterizedTest
    @CsvSource({
            "utkarshp123,CSCI222,testCourse,20/02/2050 10:00:00"
    })
    @DisplayName("ValidScheduleLectureTest")
    public void scheduleLectureTest(String facultyId, String courseId, String topic, String lecDate) {
        Assertions.assertTrue(underTest.scheduleLecture(facultyId, courseId, topic, lecDate));
    }

    @DisplayName("InValidScheduleLectureTest")
    @Test
    public void inValidScheduleLectureTest() {
        Assertions.assertFalse(underTest.scheduleLecture("utkarshp123", "CSCI222", "testCourse", "20/02/2010 10:00:00"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,CSCI222,testCourse,20/02/2050 10:00:00"
    })
    @DisplayName("ValidRescheduleLectureTest")
    public void rescheduleLectureTest(String lecId, String courseId, String topic, String lecDate) {
        Assertions.assertTrue(underTest.rescheduleLecture(lecId, courseId, topic, lecDate));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI222,1"
    })
    @DisplayName("ValidCancelLectureTest")
    public void cancelLectureTest(String courseId, String lecId) {
        Assertions.assertTrue(underTest.cancelLecture(courseId, lecId));
    }

    @ParameterizedTest
    @CsvSource({
            "utkarshp123,CSCI222"
    })
    @DisplayName("validateCourseIdTest")
    public void validateCourseIdTest(String facultyId, String courseId) {
        Assertions.assertTrue(underTest.validateCourseId(facultyId, courseId));
    }

    @ParameterizedTest
    @CsvSource({
            "utkarshp123,CSCI222,12/05/2021 15:00:00"
    })
    @DisplayName("New Course does not exist")
    public void doesCourseExistTest(String facultyId, String courseId, Date lecDate) {
        Assertions.assertFalse(underTest.doesCourseExist(facultyId, courseId, lecDate));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI222,1"
    })
    @DisplayName("validateLectureIdTest")
    public void validateLectureIdTest(String courseId, String lectureId) {
        Assertions.assertTrue(underTest.validateLectureId(courseId, lectureId));
    }

    @ParameterizedTest
    @CsvSource({
            "CSCI222,12/05/2021 15:00:00"
    })
    @DisplayName("New Lecture does not exist")
    public void doesLectureExistTest(String courseId, Date lecDate) {
        Assertions.assertFalse(underTest.doesLectureExist(courseId, lecDate));
    }

}
