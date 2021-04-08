package com.group9.server.Quiz.Student;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class GradingPersistence implements IGradingPersistence {
    final String ADD_QUIZ_GRADES = "{call addQuizGrades(?, ?, ?, ?, ?, ?, ?)}";
    final String UPDATE_QUIZ_GRADES = "{call updateQuizGrades(?, ?, ?, ?, ?, ?, ?)}";
    final String FETCH_GRADES = "{call fetchGrades(?, ?, ?)}";
    final String VIEW_GRADES = "{call viewGrades(?)}";

    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_1 = 1;
    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_2 = 2;
    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_3 = 3;
    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_4 = 4;
    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_5 = 5;
    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_6 = 6;
    private static final int GRADING_PERSISTENCE_PARAMETER_INDEX_7 = 7;
    private static final String GRADING_PERSISTENCE_OUT_PARAM = "isSuccessful";
    Connection connection;

    public GradingPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public boolean addStudentGrades(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_QUIZ_GRADES);
        return executeGradeModification(studentId, quizNumber, courseId, grades, attempt, lastAttemptDate, statement);
    }

    @Override
    public boolean updateStudentGrades(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException {
        CallableStatement statement = connection.prepareCall(UPDATE_QUIZ_GRADES);
        return executeGradeModification(studentId, quizNumber, courseId, grades, attempt, lastAttemptDate, statement);
    }

    @Override
    public ResultSet fetchPreviousGrades(String courseId, String studentId, String quizNumber) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_GRADES);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_1, courseId);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_2, studentId);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_3, quizNumber);
        ResultSet set = statement.executeQuery();
        return set;
    }

    private boolean executeGradeModification(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate, CallableStatement statement) throws SQLException {
        statement.registerOutParameter(GRADING_PERSISTENCE_PARAMETER_INDEX_7, Types.BOOLEAN);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_1, quizNumber);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_2, courseId);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_3, studentId);
        statement.setDouble(GRADING_PERSISTENCE_PARAMETER_INDEX_4, grades);
        statement.setInt(GRADING_PERSISTENCE_PARAMETER_INDEX_5, attempt);
        statement.setTimestamp(GRADING_PERSISTENCE_PARAMETER_INDEX_6, lastAttemptDate);
        statement.execute();
        return statement.getBoolean(GRADING_PERSISTENCE_OUT_PARAM);
    }

    @Override
    public ResultSet grades(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(VIEW_GRADES);
        statement.setString(GRADING_PERSISTENCE_PARAMETER_INDEX_1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
