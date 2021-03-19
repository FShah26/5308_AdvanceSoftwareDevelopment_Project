package com.group9.server.Feedback;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class FeedbackLogicTest {
    IFeedbackPersistence mockPersistence = Mockito.mock(IFeedbackPersistence.class);
    IFeedbackLogic underTest;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockPersistence.fetchFeedback("hashik")).thenReturn(null);
        when(mockPersistence.insertFeedback("hashik", "hashik", "HELLO", "utkarshp123")).thenReturn("Success");
        when(mockPersistence.insertFeedback("", "", "", "")).thenReturn("Failed");

        underTest = new FeedbackLogic(mockPersistence);
    }

    @Test
    @DisplayName("viewFeedbackTest")
    public void viewFeedbackTest() {
        Assertions.assertNotNull(underTest.viewFeedback("hashik"));
    }

    @Test
    @DisplayName("fetchFeedbackTest")
    public void fetchNotesTest() {
        Assertions.assertDoesNotThrow(() -> underTest.addFeedback("hashik", "hashik", "HELLO","utkarshp123"));
    }
}
