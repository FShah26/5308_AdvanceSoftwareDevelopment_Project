package com.group9.server.Quiz.Student;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class GradingPersistence implements IGradingPersistence {

    DBConfig config;
    Connection con;

    @Autowired
    public GradingPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public boolean addStudentGrades(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException {
        CallableStatement statement = con.prepareCall("{call add_quiz_grades(?, ?, ?, ?, ?, ?, ?)}");
        return executeGradeModification(studentId, quizNumber, courseId, grades, attempt, lastAttemptDate, statement);
    }

    @Override
    public boolean updateStudentGrades(String studentId, String quizNumber, String courseId, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException {
        CallableStatement statement = con.prepareCall("{call update_quiz_grades(?, ?, ?, ?, ?, ?, ?)}");
        return executeGradeModification(studentId, quizNumber, courseId, grades, attempt, lastAttemptDate, statement);
    }

    @Override
    public ResultSet fetchPreviousGrades(String courseId, String studentId, String quizNumber) throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_grades(?, ?, ?)}");
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
}
