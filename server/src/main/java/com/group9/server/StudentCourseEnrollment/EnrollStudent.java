package com.group9.server.StudentCourseEnrollment;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class EnrollStudent implements IEnrollStudent {
    IEnrollStudentLogic enrollStudentLogic;

    public EnrollStudent(IEnrollStudentLogic enrollStudentLogic) {
        this.enrollStudentLogic = enrollStudentLogic;
    }

    @Override
    public void enrollStudent(String userId, String courseId, String term) {
        String message = enrollStudentLogic.enrollStudent(userId, courseId, term);
        System.out.println(message);
    }

    @Override
    public String getUserId() {
        System.out.println("Enter User ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getCourseId() {
        System.out.println("Enter Course ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getTerm() {
        System.out.println("Enter Term:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void execute(String userRole, String userId) {
        String usrId = getUserId();
        String courseId = getCourseId();
        String term = getTerm();

        enrollStudent(usrId, courseId, term);
    }
}
