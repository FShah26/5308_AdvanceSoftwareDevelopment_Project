package com.group9.server.Announcements;

import com.group9.server.Dashboard.IDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;
@Component
public class AnnoucementInput implements IAnnouncementInput {

    @Autowired
    IAnnouncementLogic logic;
    @Autowired
    @Qualifier("adminDashboard")
    IDashboard dash;
    String input;
    Scanner sc;
    String role;

    @Override
    public void make_announcement(String user_role) throws SQLException {
        System.out.println("************************************************");
        System.out.println("                ENTER Announcement              ");
        System.out.println("************************************************");
        sc = new Scanner(System.in);
        System.out.print("Enter New Announcement : ");
        input = sc.nextLine();
        role = user_role;
        select_option();
    }
    @Override
    public void select_option() throws SQLException {
        String print_output;
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");

        String menuOption = sc.nextLine();
        if(menuOption.equals("1")){
            print_output =  logic.make_announcement(role,input);
            System.out.println(print_output);
            dash.dashboard();
        }
        else if(menuOption.equals("2")){
            dash.dashboard();
        }
        else
        {
            System.out.println("Please select correct option");
            select_option();
        }
    }
}
