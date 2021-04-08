package com.group9.server.Login;

import com.group9.server.Database.DatabaseConfig;
import com.group9.server.Database.ISingletonDatabase;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Component
public class UserAuthenticationPersistence implements IUserAuthPersistence {

    private static final String VERIFY_USER_CREDENTIAL = "{call VerifyUserCredentials(?, ?, ?, ?)}";
    private static final int USER_AUTH_PARAMETER_INDEX_1  = 1;
    private static final int USER_AUTH_PARAMETER_INDEX_2 = 2;
    private static final int USER_AUTH_PARAMETER_INDEX_3 = 3;
    private static final int USER_AUTH_PARAMETER_INDEX_4 = 4;
    private static final String OUT_PARAMETER = "isValid";

    Connection connection;

    public UserAuthenticationPersistence(DatabaseConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public boolean authorizeUser(String uname, String pass, String role) {
        Boolean output;
        try (
                CallableStatement statement = connection.prepareCall(VERIFY_USER_CREDENTIAL)
        ) {
            statement.registerOutParameter(USER_AUTH_PARAMETER_INDEX_4, Types.VARCHAR);
            statement.setString(USER_AUTH_PARAMETER_INDEX_1, uname);
            statement.setString(USER_AUTH_PARAMETER_INDEX_2, pass);
            statement.setString(USER_AUTH_PARAMETER_INDEX_3, role);
            statement.execute();
            output = statement.getBoolean(OUT_PARAMETER);
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = false;
        }
        return output;
    }
}
