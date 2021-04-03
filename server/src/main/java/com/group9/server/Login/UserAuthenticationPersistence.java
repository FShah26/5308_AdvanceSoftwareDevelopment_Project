package com.group9.server.Login;

import com.group9.server.Database.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserAuthenticationPersistence implements IUserAuthPersistence {

    @Autowired
    DBConfig db;

    @Override
    public boolean authorizeUser(String uname, String pass, String role) {
        String dbURL = db.url;
        String user = db.user;
        String password = db.password;
        Boolean output = false;
        final String VERIFY_CREDENTIALS = "{call VerifyUserCredentials(?, ?, ?, ?)}";
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);

                CallableStatement statement = conn.prepareCall(VERIFY_CREDENTIALS)
        ) {

            statement.registerOutParameter(4, Types.VARCHAR);
            statement.setString(1, uname);
            statement.setString(2, pass);
            statement.setString(3, role);
            statement.execute();
            output = statement.getBoolean("isValid");
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            output = false;
        }
        return output;
    }
}
