package com.group9.server.Login;

import com.group9.server.Login.IUserAuthDao;
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
public class UserAuthenticationPersistence extends JdbcDaoSupport implements IUserAuthDao {

    @Autowired
    DBConfig db;

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplateObject;


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public boolean AuthorizeUser(String uname, String pass, String role) {
//        SimpleJdbcCall jdbcCall = new
//                SimpleJdbcCall(dataSource).withProcedureName("VerifyUserCredentials");
//        SqlParameterSource inParams = new MapSqlParameterSource()
//                .addValue("userId", uname)
//                .addValue("password", pass)
//                .addValue("userrole", role);
//        Map results = jdbcCall.execute(inParams);
//        return (boolean) results.get("isValid");

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
