package com.group9.server.Announcements.Admin;

import com.group9.server.Dashboard.IDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.out;

@Component
public class FacultyAnnouncement implements IAnnouncementInput {

    @Autowired
    IAnnouncementLogic announcementLogic;
    @Autowired
    @Qualifier("facultyDashboard")
    IDashboard dash;
    String input;
    Scanner sc;
    String userRole;
    String userId;
    String courseId;


    @Override
    public void make_announcement(String userRole, String userId) throws SQLException {
        this.userRole = userRole;
        this.userId = userId;
        System.out.println("************************************************");
        System.out.println("                ENTER Announcement              ");
        System.out.println("************************************************");
        this.courseId = getValidCourseIdInput();
        sc = new Scanner(System.in);
        System.out.print("Enter New Announcement : ");
        input = sc.nextLine();
        select_option();
    }

    @Override
    public void select_option() throws SQLException {
        String print_output;
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");

        String menuOption = sc.nextLine();
        if (menuOption.equals("1")) {
            print_output = announcementLogic.make_announcement(userRole, courseId, input, userId);
            System.out.println(print_output);
            dash.showDashboard();
        }
        else if(menuOption.equals("2")){
            dash.showDashboard();
        }
        else
        {
            System.out.println("Please select correct option");
            select_option();
        }
    }

    public String getValidCourseIdInput() {
        int state = 0;
        Scanner sc = new Scanner(System.in);
        out.println("Enter CourseId: ");
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
}
