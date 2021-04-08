package com.group9.server.Login;

import com.group9.server.Database.DBConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class UserAuthenticationPersistence implements IUserAuthPersistence {

    private static final String VERIFY_USER_CREDENTIAL = "{call VerifyUserCredentials(?, ?, ?, ?)}";
    private static final int USER_NAME = 1;
    private static final int PASSWORD = 2;
    private static final int USER_ROLE = 3;
    private static final int OUTPUT = 4;
    private static final String FETCH_OUTPUT_VALUE = "isValid";

    Connection connection;

    public UserAuthenticationPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public boolean authorizeUser(String uname, String pass, String role) {
        Boolean output;
        final String VERIFY_CREDENTIALS = VERIFY_USER_CREDENTIAL;
        try (
                CallableStatement statement = connection.prepareCall(VERIFY_CREDENTIALS)
        ) {

            statement.registerOutParameter(OUTPUT, Types.VARCHAR);
            statement.setString(USER_NAME, uname);
            statement.setString(PASSWORD, pass);
            statement.setString(USER_ROLE, role);
            statement.execute();
            output = statement.getBoolean(FETCH_OUTPUT_VALUE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = false;
        }
        return output;
    }
}
