package com.group9.server.Quiz;

import com.group9.server.Feedback.FeedbackLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class QuizLogicTest {
    IQuizPersistence mockPersistence = Mockito.mock(IQuizPersistence.class);
    IQuizLogic underTest;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockPersistence.insertQuestion("CSCI123", "2", "Check", "optionA","optionB","optionC","optionD","C")).thenReturn("Success");
        underTest = new QuizLogic(mockPersistence);
    }

    @Test
    @DisplayName("addQuestionTest")
    public void addQuizTest() {
        Assertions.assertDoesNotThrow(() -> underTest.addQuestion("CSCI123", "2", "Check question","optionA","optionB","optionC","optionD","B"));
    }
}
