package com.group9.server.Notes;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StudentNotes implements IStudentNotes {
    private static final int ADD_NOTES_OPTION = 1;
    private static final int VIEW_NOTES_OPTION = 2;
    private static final int NOT_NULL_CHECKER = 0;
    INotesLogic notesLogic;
    Scanner scanner;

    public StudentNotes(INotesLogic notesLogic) {
        this.notesLogic = notesLogic;
        scanner = new Scanner(System.in);
    }

    @Override
    public void viewNotes(String studentId, String courseId) {
        NotesList list = notesLogic.viewNotes(studentId, courseId);

        System.out.println("Notes for course ID: " + courseId);
        if (list.notes.size() == NOT_NULL_CHECKER) {
            System.out.println("Looks like you don't have any notes for this course");
        } else {
            for (String note : list.notes) {
                System.out.println("--------------------");
                System.out.println(note);
            }
        }
    }

    @Override
    public void addNotes(String studentId, String courseId, String notes) {
        String message = notesLogic.addNotes(studentId, courseId, notes);
        System.out.println(message);
    }

    public String getCourseInput() {
        System.out.println("Enter the course ID:");
        return scanner.nextLine();
    }

    @Override
    public String getNotesText() {
        System.out.println("Enter the text for the notes: ");
        return scanner.nextLine();
    }

    private int getOptionInput() {
        System.out.println("===== NOTES =====\n");
        System.out.println("1. Add Notes");
        System.out.println("2. View Notes");
        int option = Integer.parseInt(scanner.nextLine());
        return option;
    }

    @Override
    public void execute(String userRole, String userId) {
        int input = getOptionInput();
        String course = getCourseInput();

        if (input == ADD_NOTES_OPTION) {
            String text = getNotesText();
            addNotes(userId, course, text);
        } else if (input == VIEW_NOTES_OPTION) {
            viewNotes(userId, course);
        }
    }
}
