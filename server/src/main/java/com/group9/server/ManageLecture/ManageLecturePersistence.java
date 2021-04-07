package com.group9.server.ManageLecture;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;

@Component
public class ManageLecturePersistence implements IManageLecturePersistence {
    final String FETCH_LECTURES_BY_FACULTY = "{call fetchLecturesByFaculty(?)}";
    final String ADD_LECTURE = "{call addLecture(?, ?, ?, ?,?)}";
    final String UPDATE_LECTURE = "{call updateLecture(?, ?, ?, ?)}";
    final String DELETE_LECTURE = "{call deleteLecture(?, ?)}";
    final String GET_ASSIGNED_COURSES = "{call getAssignedCourses(?)}";
    final String FETCH_LECTURES_BY_COURSE = "{call fetchLecturesByCourse(?)}";
    Connection connection;

    public ManageLecturePersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet getAllLectures(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_LECTURES_BY_FACULTY);
        statement.setString(1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public boolean addLecture(String facultyId, String courseId, String lecTopic, Date lecDate) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_LECTURE);
        statement.registerOutParameter(5, Types.BOOLEAN);
        statement.setString(1, facultyId);
        statement.setString(2, courseId);
        statement.setString(3, lecTopic);
        statement.setTimestamp(4, new java.sql.Timestamp(lecDate.getTime()));
        statement.execute();

        return statement.getBoolean("isSuccessful");
    }

    @Override
    public boolean updateLecture(String lecId, String lecAgenda, Date lecDate) throws SQLException {
        CallableStatement statement = connection.prepareCall(UPDATE_LECTURE);
        statement.registerOutParameter(4, Types.BOOLEAN);
        statement.setString(1, lecId);
        statement.setString(2, lecAgenda);
        statement.setTimestamp(3, new java.sql.Timestamp(lecDate.getTime()));
        statement.execute();
        return statement.getBoolean("isSuccessful");
    }

    @Override
    public boolean deleteLecture(String lectureId) throws SQLException {
        CallableStatement statement = connection.prepareCall(DELETE_LECTURE);
        statement.registerOutParameter(2, Types.BOOLEAN);
        statement.setString(1, lectureId);
        statement.execute();
        return statement.getBoolean("isSuccessful");
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(GET_ASSIGNED_COURSES);
        statement.setString(1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    public ResultSet getCourseLectures(String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_LECTURES_BY_COURSE);
        statement.setString(1, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
