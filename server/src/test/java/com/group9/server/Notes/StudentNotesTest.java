package com.group9.server.Notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class StudentNotesTest {
    StudentNotes underTest;
    INotesLogic mockNotesLogic = Mockito.mock(INotesLogic.class);
    NotesList list;

    @BeforeEach
    public void setUp() {
        underTest = new StudentNotes(mockNotesLogic);
    }

    @DisplayName("viewNotesTest")
    @ParameterizedTest
    @CsvSource({
            "CSCI00",
    })
    public void viewNotesTest(String course) {
        try {
            list = new NotesList(course);
            when(mockNotesLogic.viewNotes("hashik", "INVALID COURSE")).thenReturn(new NotesList(course));
            list.notes.add("TEST");
            list.notes.add("TEST1");
            list.notes.add("TEST2");
            list.notes.add("TEST3");
            when(mockNotesLogic.viewNotes("hashik", course)).thenReturn(list);

            underTest.viewNotes("hashik", "INVALID COURSE");
            underTest.viewNotes("hashik", course);
        } catch (Exception e) {
            Assertions.fail("Failed with exception");
        }
    }

    @DisplayName("addNotesTest")
    @ParameterizedTest
    @CsvSource({
            "hashik, CSCI00, TEST NOTES",
    })
    public void addNotesTest(String student, String sub, String notes) {
        when(mockNotesLogic.addNotes(student, sub, notes)).thenReturn("Success");
        try {
            underTest.addNotes(student, sub, notes);
        } catch (Exception e) {
            Assertions.fail("Failed with exception.");
        }
    }
}
