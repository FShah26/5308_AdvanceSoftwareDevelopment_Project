package com.group9.server.Notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StudentNotes implements IStudentNotes {
    INotesLogic notesLogic;

    @Autowired
    public StudentNotes(INotesLogic notesLogic) {
        this.notesLogic = notesLogic;
    }

    @Override
    public void viewNotes(String studentId, String courseId) {
        NotesList list = notesLogic.viewNotes(studentId, courseId);

        System.out.println("Notes for course ID: " + courseId);
        if (list.notes.size() == 0) {
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
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getNotesText() {
        System.out.println("Enter the text for the notes: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void execute(String userRole, String userId) {
        String course = getCourseInput();
        viewNotes(userId, course);

        course = getCourseInput();
        String text = getNotesText();

        addNotes(userId, course, text);
    }
}
