package com.group9.server.UserCreation;

import com.group9.server.Database.DatabaseConfig;
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
    private static final int ADD_USER_PARAMETER_INDEX_1 = 1;
    private static final int ADD_USER_PARAMETER_INDEX_2 = 2;
    private static final int ADD_USER_PARAMETER_INDEX_3 = 3;
    private static final int ADD_USER_PARAMETER_INDEX_4 = 4;
    private static final int ADD_USER_PARAMETER_INDEX_5 = 5;
    private static final int ADD_USER_PARAMETER_INDEX_6 = 6;
    private static final String RETURN_MESSAGE = "message";
    Connection connection;

    public AddUserPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public void addUser(String id, String userId, String password, String userType) {
        String output = "";
        try (
                CallableStatement statement = connection.prepareCall(CREATE_NEW_USER)
        ) {

            statement.registerOutParameter(ADD_USER_PARAMETER_INDEX_5, Types.VARCHAR);
            statement.setString(ADD_USER_PARAMETER_INDEX_1, id);
            statement.setString(ADD_USER_PARAMETER_INDEX_2, userId);
            statement.setString(ADD_USER_PARAMETER_INDEX_3, password);
            statement.setString(ADD_USER_PARAMETER_INDEX_4, userType);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void addUserDetails(String userId, String userType, String name, String emailAddress, String department) {
        String output = "";
        try (
                CallableStatement statement = connection.prepareCall(USER_DETAILS)
        ) {

            statement.registerOutParameter(ADD_USER_PARAMETER_INDEX_6, Types.VARCHAR);
            statement.setString(ADD_USER_PARAMETER_INDEX_1, userId);
            statement.setString(ADD_USER_PARAMETER_INDEX_2, userType);
            statement.setString(ADD_USER_PARAMETER_INDEX_3, name);
            statement.setString(ADD_USER_PARAMETER_INDEX_4, emailAddress);
            statement.setString(ADD_USER_PARAMETER_INDEX_5, department);
            statement.execute();
            output = statement.getString(RETURN_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = "Error Caught";
        }
        System.out.println(output);

    }
}

