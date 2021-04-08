package com.group9.server.Quiz.Student;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class GradingPersistence implements IGradingPersistence {
    final String ADD_QUIZ_GRADES = "{call addQuizGrades(?, ?, ?, ?, ?, ?, ?)}";
    final String UPDATE_QUIZ_GRADES = "{call updateQuizGrades(?, ?, ?, ?, ?, ?, ?)}";
    final String FETCH_GRADES = "{call fetchGrades(?, ?, ?)}";
    final String VIEW_GRADES = "{call viewGrades(?)}";
    Connection connection;

    public GradingPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
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
        statement.setString(1, courseId);
        statement.setString(2, studentId);
        statement.setString(3, quizNumber);
        ResultSet set = statement.executeQuery();
        return set;
    }

    private boolean executeGradeModification(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate, CallableStatement statement) throws SQLException {
        statement.registerOutParameter(7, Types.BOOLEAN);
        statement.setString(1, quizNumber);
        statement.setString(2, courseId);
        statement.setString(3, studentId);
        statement.setDouble(4, grades);
        statement.setInt(5, attempt);
        statement.setTimestamp(6, lastAttemptDate);
        statement.execute();
        return statement.getBoolean("isSuccessful");
    }

    @Override
    public ResultSet grades(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(VIEW_GRADES);
        statement.setString(1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }
}
