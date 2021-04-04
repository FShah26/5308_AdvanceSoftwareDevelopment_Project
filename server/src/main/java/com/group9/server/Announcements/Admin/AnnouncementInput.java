package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AnnouncementInput implements IAnnouncementInput {

    IAnnouncementLogic announcementLogic;
    String input;
    Scanner scanner;
    String userRole;
    String userId;

    public AnnouncementInput(IAnnouncementLogic announcementLogic) {
        this.announcementLogic = announcementLogic;
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
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");
        String menuOption = scanner.nextLine();

        switch (menuOption) {
            case "1":
                printOutput = announcementLogic.makeAnnouncement(userRole, null, input, userId);
                System.out.println(printOutput);
                break;
            case "2":
                System.out.println("Back to Dashboard");
                break;
            default:
                System.out.println("Please select correct option");
                selectOption();
        }
    }

    @Override
    public void execute(String userRole, String userId) {
        announcement(userRole, userId);
    }
}
