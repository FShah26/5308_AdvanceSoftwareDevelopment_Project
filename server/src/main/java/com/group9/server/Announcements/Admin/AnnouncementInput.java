package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

import java.sql.SQLException;
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
    public void announcement(String userRole, String userId)  {
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
        String print_output;
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");

        String menuOption = scanner.nextLine();
        if (menuOption.equals("1")) {
            print_output = announcementLogic.makeAnnouncement(userRole, null, input, userId);
            System.out.println(print_output);
        } else if (menuOption.equals("2")) {
            System.out.println("Back to Dashboard");
        } else {
            System.out.println("Please select correct option");
            selectOption();
        }
    }
}
