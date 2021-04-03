package com.group9.server.Quiz;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class QuizPersistence implements IQuizPersistence {

    Connection connection;

    @Autowired
    public QuizPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String insertQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) throws SQLException {
        final String ADD_QUESTION = "{call add_question(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        CallableStatement statement = connection.prepareCall(ADD_QUESTION);
        statement.registerOutParameter(9, Types.VARCHAR);
        statement.setString(1, courseId);
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
        final String FETCH_QUIZ = "{call fetch_quiz(?,?)}";
        CallableStatement statement = connection.prepareCall(FETCH_QUIZ);
        statement.setString(1, courseId);
        statement.setString(2, quizNumber);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchCourseQuiz(String courseId) throws SQLException {
        final String FETCh_COURSE = "{call fetch_course_quiz(?)}";
        CallableStatement statement = connection.prepareCall(FETCh_COURSE);
        statement.setString(1, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentId) throws SQLException {
        final String REGISTERED_COURSE = "{call RegisteredCourses(?)}";
        CallableStatement statement = connection.prepareCall(REGISTERED_COURSE);
        statement.setString(1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }


}
