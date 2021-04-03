package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyAnnouncement implements IAnnouncementInput {

    IAnnouncementLogic announcementLogic;
    String input;
    Scanner scanner;
    String userRole;
    String userId;
    String courseId;


    public FacultyAnnouncement(IAnnouncementLogic announcementLogic) {
        this.announcementLogic = announcementLogic;
    }


    @Override
    public void announcement(String userRole, String userId) {
        this.userRole = userRole;
        this.userId = userId;
        System.out.println("************************************************");
        System.out.println("                ENTER Announcement              ");
        System.out.println("************************************************");
        this.courseId = getValidCourseIdInput();
        scanner = new Scanner(System.in);
        System.out.print("Enter New Announcement : ");
        input = scanner.nextLine();
        selectOption();
    }

    @Override
    public void selectOption() {
        String print_output;
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");

        String menuOption = scanner.nextLine();
        if (menuOption.equals("1")) {
            print_output = announcementLogic.makeAnnouncement(userRole, courseId, input, userId);
            System.out.println(print_output);
        } else if (menuOption.equals("2")) {
            System.out.println("Navigating back to Dashboard");
        } else {
            System.out.println("Please select correct option");
            selectOption();
        }
    }

    public String getValidCourseIdInput() {
        int state = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CourseId: ");
        String courseId = sc.nextLine();
        while ((state = announcementLogic.validateCourseId(this.userId, courseId)) == 0) {
            out.println("Enter a valid courseID: ");
            courseId = sc.nextLine();
        }
        if (state == -1) {
            return null;
        }
        return courseId;
    }

    @Override
    public void execute(String userRole, String userId) {
        announcement(userRole, userId);
    }
}
