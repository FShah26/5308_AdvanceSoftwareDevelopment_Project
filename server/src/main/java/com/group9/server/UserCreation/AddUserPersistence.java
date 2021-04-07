package com.group9.server.UserCreation;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class AddUserPersistence implements IAddUserPersistence {
    final String CREATE_NEW_USER = "{call createNewUser(?, ?, ?, ?, ?)}";
    final String USER_DETAILS = "{call addUserDetails(?, ?, ?, ?, ?, ?)}";
    Connection connection;

    public AddUserPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public void addUser(String id, String userId, String password, String userType) {
        String output = "";
        try (
                CallableStatement statement = connection.prepareCall(CREATE_NEW_USER)
        ) {

            statement.registerOutParameter(5, Types.VARCHAR);
            statement.setString(1, id);
            statement.setString(2, userId);
            statement.setString(3, password);
            statement.setString(4, userType);
            statement.execute();
            output = statement.getString("message");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Catched";
        }

    }

    @Override
    public void addUserDetails(String userId, String userType, String name, String emailAddress, String department) {
        String output = "";
        try (
                CallableStatement statement = connection.prepareCall(USER_DETAILS)
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

