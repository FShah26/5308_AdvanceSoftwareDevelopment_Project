package com.group9.server.Announcements.Admin;

import com.group9.server.Common.IUserConfirmation;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyAnnouncement implements IAnnouncementInput {

    IAnnouncementLogic announcementLogic;
    IUserConfirmation userConfirmation;
    String input;
    Scanner scanner;
    String userRole;
    String userId;
    String courseId;


    public FacultyAnnouncement(IAnnouncementLogic announcementLogic, IUserConfirmation userConfirmation) {
        this.announcementLogic = announcementLogic;
        this.userConfirmation = userConfirmation;
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
        String printOutput;
        userConfirmation.showUserConfirmationOptions();
        if (userConfirmation.getUserConfirmation()) {
            printOutput = announcementLogic.makeAnnouncement(userRole, courseId, input, userId);
            System.out.println(printOutput);
        } else {
            System.out.println("Navigating back to Dashboard");
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
