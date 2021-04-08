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

    Connection connection;

    public UserAuthenticationPersistence(DBConfig config, ISingletonDatabase database) throws SQLException {
        ISingletonDatabase databaseInstance = database.getInstance();
        connection = databaseInstance.getConnection(config);
    }

    @Override
    public boolean authorizeUser(String uname, String pass, String role) {
        Boolean output = false;
        final String VERIFY_CREDENTIALS = "{call VerifyUserCredentials(?, ?, ?, ?)}";
        try (
                CallableStatement statement = connection.prepareCall(VERIFY_CREDENTIALS)
        ) {

            statement.registerOutParameter(4, Types.VARCHAR);
            statement.setString(1, uname);
            statement.setString(2, pass);
            statement.setString(3, role);
            statement.execute();
            output = statement.getBoolean("isValid");
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = false;
        }
        return output;
    }
}
