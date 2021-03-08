package com.group9.server.Notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class NotesLogicTest {
    INotesPersistence mockPersistence = Mockito.mock(INotesPersistence.class);
    INotesLogic underTest;

    @BeforeEach
    public void setUp() throws SQLException {
        when(mockPersistence.fetchNotes("hashik", "CSCI1000")).thenReturn(null);
        when(mockPersistence.insertNotes("hashik", "CSCI123", "HELLO")).thenReturn("Success");
        when(mockPersistence.insertNotes("", "", "")).thenReturn("Failed");

        underTest = new NotesLogic(mockPersistence);
    }

    @Test
    @DisplayName("viewNotesTest")
    public void viewNotesTest() {
        Assertions.assertNotNull(underTest.viewNotes("hashik", "CSCI1000"));
    }

    @Test
    @DisplayName("fetchNotesTest")
    public void fetchNotesTest() {
        Assertions.assertDoesNotThrow(() -> underTest.addNotes("hashik", "CSCI123", "HELLO"));
    }
}
