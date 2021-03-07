package com.group9.server.UserCreation;

import com.group9.server.cnfg.DBConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

@Component
public class AddUserPersistence implements IAddUserPersistence {

    @Autowired
    DBConfig db;

    @Override
    public void addUser(String id, String userid, String password, String user_type) {
        String dbURL = db.url;
        String user = db.user;
        String pass = db.password;
        String output="";
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
            output ="Error Catched";
        }
        System.out.println(output);
    }
}

