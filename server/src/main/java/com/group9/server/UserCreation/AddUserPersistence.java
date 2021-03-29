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
    public void addUser(String id, String userid, String password, String user_type) {
        String dbURL = db.url;
        String user = db.user;
        String pass = db.password;
        String output = "";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, pass);
                CallableStatement statement = conn.prepareCall("{call Create_NewUser(?, ?, ?, ?, ?)}");
        ) {

            statement.registerOutParameter(5, Types.VARCHAR);
            statement.setString(1, id);
            statement.setString(2, userid);
            statement.setString(3, password);
            statement.setString(4, user_type);
            statement.execute();
            output = statement.getString("msg");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }
    }

    @Override
    public void addUserDetails(String userid, String user_type, String name, String email_address, String department) {
        String dbURL = db.url;
        String user = db.user;
        String pass = db.password;
        String output = "";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, pass);
                CallableStatement statement = conn.prepareCall("{call add_user_details(?, ?, ?, ?, ?, ?)}");
        ) {

            statement.registerOutParameter(6, Types.VARCHAR);
            statement.setString(1, userid);
            statement.setString(2, user_type);
            statement.setString(3, name);
            statement.setString(4, email_address);
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

