package com.group9.server.Quiz;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class QuizPersistence implements IQuizPersistence {

    DBConfig config;
    Connection con;

    @Autowired
    public QuizPersistence(DBConfig config) throws SQLException {
        this.config = config;
        con = DriverManager.getConnection(config.url, config.user, config.password);
    }

    @Override
    public String insertQuestion(String course_Id, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) throws SQLException {
        CallableStatement statement = con.prepareCall("{call add_question(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        statement.registerOutParameter(9, Types.VARCHAR);
        statement.setString(1, course_Id);
        statement.setString(2, quizNumber);
        statement.setString(3, question);
        statement.setString(4, optionA);
        statement.setString(5, optionB);
        statement.setString(6, optionC);
        statement.setString(7, optionD);
        statement.setString(8, answer);

        statement.execute();

        return statement.getString("message");
    }

    @Override
    public ResultSet fetchQuiz(String courseId, String quizNumber) throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_quiz(?,?)}");
        statement.setString(1, courseId);
        statement.setString(2, quizNumber);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchCourseQuiz(String courseId) throws SQLException {
        CallableStatement statement = con.prepareCall("{call fetch_course_quiz(?)}");
        statement.setString(1, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentID) throws SQLException {
        CallableStatement statement = con.prepareCall("{call RegisteredCourses(?)}");
        statement.setString(1, studentID);
        ResultSet set = statement.executeQuery();
        return set;
    }


}
