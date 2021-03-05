package com.group9.server.Announcements;

import com.group9.server.Modules.Interface.IDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
    @Override
    public void make_announcement() {
        System.out.println("************************************************");
        System.out.println("                ENTER Announcement              ");
        System.out.println("************************************************");
        sc = new Scanner(System.in);
        System.out.print("Enter New Announcement : ");
        input = sc.nextLine();
        select_option();
    }
    @Override
    public void select_option() {
        String print_output;
        System.out.println("-->Press 1 to confirm");
        System.out.println("-->Press 2 to Cancel");

        String menuOption = sc.nextLine();
        if(menuOption.equals("1")){
            print_output =  logic.make_announcement("admin",input);
            System.out.println(print_output);
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
