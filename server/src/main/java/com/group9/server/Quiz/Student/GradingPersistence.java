package com.group9.server.Quiz.Student;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;

public class GradingPersistence implements IGradingPersistence{

    DBConfig config;
    Connection con;

    @Autowired
    public GradingPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public boolean addStudentGrades(String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException {
        CallableStatement statement = con.prepareCall("{call add_quiz_grades(?, ?, ?, ?,?,?)}");
        return executeGradeModification(studentId, quizNumber, grades, attempt, lastAttemptDate, statement);
    }

    @Override
    public boolean updateStudentGrades(String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptDate) throws SQLException {
        CallableStatement statement = con.prepareCall("{call update_quiz_grades(?, ?, ?, ?,?,?)}");
        return executeGradeModification(studentId, quizNumber, grades, attempt, lastAttemptDate, statement);
    }

    private boolean executeGradeModification(String studentId, String quizNumber, double grades, int attempt, Timestamp lastAttemptDate, CallableStatement statement) throws SQLException {
        statement.registerOutParameter(6, Types.BOOLEAN);
        statement.setString(1, quizNumber);
        statement.setString(2, studentId);
        statement.setDouble(3, grades);
        statement.setInt(4, attempt);
        statement.setTimestamp(5,lastAttemptDate);
        statement.execute();
        return statement.getBoolean("isSuccessful");
    }
}
