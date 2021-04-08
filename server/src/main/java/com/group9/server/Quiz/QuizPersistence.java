package com.group9.server.Quiz;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class QuizPersistence implements IQuizPersistence {
    final String ADD_QUESTION = "{call addQuestion(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    final String FETCH_QUIZ = "{call fetchQuiz(?,?)}";
    final String FETCH_COURSE = "{call fetchCourseQuiz(?)}";
    final String REGISTERED_COURSE = "{call RegisteredCourse(?)}";
    Connection connection;

    public QuizPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String insertQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) throws SQLException {
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
        CallableStatement statement = connection.prepareCall(FETCH_QUIZ);
        statement.setString(1, courseId);
        statement.setString(2, quizNumber);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchCourseQuiz(String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_COURSE);
        statement.setString(1, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(REGISTERED_COURSE);
        statement.setString(1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }


}
