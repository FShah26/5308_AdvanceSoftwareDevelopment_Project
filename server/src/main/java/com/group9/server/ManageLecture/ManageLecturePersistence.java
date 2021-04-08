package com.group9.server.ManageLecture;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.Date;

@Component
public class ManageLecturePersistence implements IManageLecturePersistence {
    private static final String FETCH_LECTURES_BY_FACULTY = "{call fetchLecturesByFaculty(?)}";
    private static final String ADD_LECTURE = "{call addLecture(?, ?, ?, ?,?)}";
    private static final String UPDATE_LECTURE = "{call updateLecture(?, ?, ?, ?)}";
    private static final String DELETE_LECTURE = "{call deleteLecture(?, ?)}";
    private static final String GET_ASSIGNED_COURSES = "{call getAssignedCourses(?)}";
    private static final String FETCH_LECTURES_BY_COURSE = "{call fetchLecturesByCourse(?)}";
    private static final int MANAGE_LECTURE_PARAMETER_INDEX_1 = 1;
    private static final int MANAGE_LECTURE_PARAMETER_INDEX_2 = 2;
    private static final int MANAGE_LECTURE_PARAMETER_INDEX_3 = 3;
    private static final int MANAGE_LECTURE_PARAMETER_INDEX_4 = 4;
    private static final int MANAGE_LECTURE_PARAMETER_INDEX_5 = 5;
    private static final String PARAMETER_NAME = "isSuccessful";

    Connection connection;

    public ManageLecturePersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public ResultSet getAllLectures(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_LECTURES_BY_FACULTY);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public boolean addLecture(String facultyId, String courseId, String lecTopic, Date lecDate) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_LECTURE);
        statement.registerOutParameter(MANAGE_LECTURE_PARAMETER_INDEX_5, Types.BOOLEAN);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_1, facultyId);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_2, courseId);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_3, lecTopic);
        statement.setTimestamp(MANAGE_LECTURE_PARAMETER_INDEX_4, new java.sql.Timestamp(lecDate.getTime()));
        statement.execute();

        return statement.getBoolean(PARAMETER_NAME);
    }

    @Override
    public boolean updateLecture(String lecId, String lecAgenda, Date lecDate) throws SQLException {
        CallableStatement statement = connection.prepareCall(UPDATE_LECTURE);
        statement.registerOutParameter(MANAGE_LECTURE_PARAMETER_INDEX_4, Types.BOOLEAN);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_1, lecId);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_2, lecAgenda);
        statement.setTimestamp(MANAGE_LECTURE_PARAMETER_INDEX_3, new java.sql.Timestamp(lecDate.getTime()));
        statement.execute();
        return statement.getBoolean(PARAMETER_NAME);
    }

    @Override
    public boolean deleteLecture(String lectureId) throws SQLException {
        CallableStatement statement = connection.prepareCall(DELETE_LECTURE);
        statement.registerOutParameter(MANAGE_LECTURE_PARAMETER_INDEX_2, Types.BOOLEAN);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_1, lectureId);
        statement.execute();
        return statement.getBoolean(PARAMETER_NAME);
    }

    public ResultSet getFacultyCourses(String facultyId) throws SQLException {
        CallableStatement statement = connection.prepareCall(GET_ASSIGNED_COURSES);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_1, facultyId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    public ResultSet getCourseLectures(String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_LECTURES_BY_COURSE);
        statement.setString(MANAGE_LECTURE_PARAMETER_INDEX_1, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
