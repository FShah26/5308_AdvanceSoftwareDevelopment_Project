package com.group9.server.ManageLecture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public interface IManageLecturePersistence {
    ResultSet getFacultyCourses(String facultyId) throws SQLException;
    ResultSet getAllLectures(String courseId) throws SQLException;
    boolean addLecture(String facultyId,String courseId, String lecAgenda, Date lecDate)throws SQLException;
    boolean updateLecture(String lecId,String courseId, String lecAgenda, Date lecDate)throws SQLException;
    boolean deleteLecture(String lectureId)throws SQLException;
}
