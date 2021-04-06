package com.group9.server.UserCreation;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUser implements IAddUser {
    final String TO_PROCEED = "true";
    IAddUserLogic addUserLogic;

    public AddUser(IAddUserLogic addUserLogic) {
        this.addUserLogic = addUserLogic;
    }

    @Override
    public void addUser(String id, String userId, String password, String userType) {
        String message = addUserLogic.addUser(id, userId, password, userType);
        System.out.println(message);
    }

    @Override
    public void addUserDetails(String userId, String userType, String name, String emailAddress, String department) {
        String message = addUserLogic.addUserDetails(userId, userType, name, emailAddress, department);
        System.out.println(message);
    }

    @Override
    public String getId() {
        System.out.println("Enter ID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getUserId() {
        System.out.println("Enter UserID:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getPassword() {
        System.out.println("Enter Password:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getUserType() {
        System.out.println("Enter Type of User:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getName() {
        System.out.println("Enter name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getEmail() {
        System.out.println("Enter Email:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String getDepartment() {
        System.out.println("Enter Department:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void execute(String userRole, String userId) {
        String id = getId();
        String usrId = getUserId();
        String password = getPassword();
        String userType = getUserType();
        String name = getName();
        String email = getEmail();
        String department = getDepartment();

        addUser(id, usrId, password, userType);
        addUserDetails(usrId, userType, name, email, department);
    }
}
