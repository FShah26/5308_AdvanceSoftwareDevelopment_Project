package com.group9.server.Login;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
        Boolean output=false;
        try (
                Connection conn = DriverManager.getConnection(dbURL, user, password);
                CallableStatement statement = conn.prepareCall("{call VerifyUserCredentials(?, ?, ?, ?)}");
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
            output =false;
        }
        return output;
    }
}
