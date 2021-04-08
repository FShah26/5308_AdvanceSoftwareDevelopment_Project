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
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_1 = 1;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_2 = 2;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_3 = 3;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_4 = 4;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_5 = 5;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_6 = 6;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_7 = 7;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_8 = 8;
    private static final int QUIZ_PERSISTENCE_PARAMETER_INDEX_9 = 9;
    private static final String QUIZ_PERSISTENCE_MESSAGE = "message";
    Connection connection;

    public QuizPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public String insertQuestion(String courseId, String quizNumber, String question, String optionA, String optionB, String
            optionC, String optionD, String answer) throws SQLException {
        CallableStatement statement = connection.prepareCall(ADD_QUESTION);
        statement.registerOutParameter(QUIZ_PERSISTENCE_PARAMETER_INDEX_9, Types.VARCHAR);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_1, courseId);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_2, quizNumber);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_3, question);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_4, optionA);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_5, optionB);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_6, optionC);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_7, optionD);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_8, answer);

        statement.execute();

        return statement.getString(QUIZ_PERSISTENCE_MESSAGE);
    }

    @Override
    public ResultSet fetchQuiz(String courseId, String quizNumber) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_QUIZ);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_1, courseId);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_2, quizNumber);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchCourseQuiz(String courseId) throws SQLException {
        CallableStatement statement = connection.prepareCall(FETCH_COURSE);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_1, courseId);
        ResultSet set = statement.executeQuery();
        return set;
    }

    @Override
    public ResultSet fetchRegisteredCourses(String studentId) throws SQLException {
        CallableStatement statement = connection.prepareCall(REGISTERED_COURSE);
        statement.setString(QUIZ_PERSISTENCE_PARAMETER_INDEX_1, studentId);
        ResultSet set = statement.executeQuery();
        return set;
    }


}
