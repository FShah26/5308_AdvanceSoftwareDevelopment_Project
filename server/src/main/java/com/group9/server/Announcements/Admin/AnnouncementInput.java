package com.group9.server.Announcements.Admin;

import com.group9.server.Common.IUserConfirmation;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AnnouncementInput implements IAnnouncementInput {

    IAnnouncementLogic announcementLogic;
    IUserConfirmation userConfirmation;
    String input;
    Scanner scanner;
    String userRole;
    String userId;

    public AnnouncementInput(IAnnouncementLogic announcementLogic, IUserConfirmation userConfirmation) {
        this.announcementLogic = announcementLogic;
        this.userConfirmation = userConfirmation;
    }

    @Override
    public void announcement(String userRole, String userId) {
        System.out.println("************************************************");
        System.out.println("                ENTER Announcement              ");
        System.out.println("************************************************");
        scanner = new Scanner(System.in);
        System.out.print("Enter New Announcement : ");
        input = scanner.nextLine();
        this.userRole = userRole;
        this.userId = userId;
        selectOption();
    }

    @Override
    public void selectOption() {
        String printOutput;
        userConfirmation.showUserConfirmationOptions();
        if (userConfirmation.getUserConfirmation()) {
            printOutput = announcementLogic.makeAnnouncement(userRole, null, input, userId);
            System.out.println(printOutput);
        } else {
            System.out.println("Navigating back to Dashboard");
        }
    }

    @Override
    public void execute(String userRole, String userId) {
        announcement(userRole, userId);
    }
}
