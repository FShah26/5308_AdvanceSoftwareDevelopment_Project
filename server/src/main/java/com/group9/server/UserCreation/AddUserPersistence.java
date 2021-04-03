package com.group9.server.UserCreation;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class AddUserPersistence implements IAddUserPersistence {

    @Autowired
    DBConfig db;

    @Override
    public void addUser(String id, String userId, String password, String userType) {
        String dbURL = db.url;
        String user = db.user;
        String pass = db.password;
        String output = "";
        final String CREATE_NEW_USER = "{call Create_NewUser(?, ?, ?, ?, ?)}";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, pass);
                CallableStatement statement = conn.prepareCall(CREATE_NEW_USER);
        ) {

            statement.registerOutParameter(5, Types.VARCHAR);
            statement.setString(1, id);
            statement.setString(2, userId);
            statement.setString(3, password);
            statement.setString(4, userType);
            statement.execute();
            output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
    }

    @Override
    public void addUserDetails(String userId, String userType, String name, String emailAddress, String department) {
        String dbURL = db.url;
        String user = db.user;
        String pass = db.password;
        String output = "";
        final String USER_DETAILS = "{call add_user_details(?, ?, ?, ?, ?, ?)}";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, pass);
                CallableStatement statement = conn.prepareCall(USER_DETAILS);
        ) {

            statement.registerOutParameter(6, Types.VARCHAR);
            statement.setString(1, userId);
            statement.setString(2, userType);
            statement.setString(3, name);
            statement.setString(4, emailAddress);
            statement.setString(5, department);
            statement.execute();
            output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }

        System.out.println(output);

    }
}

