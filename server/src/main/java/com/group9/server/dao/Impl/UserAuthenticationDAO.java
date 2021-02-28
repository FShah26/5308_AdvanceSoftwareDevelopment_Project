package com.group9.server.dao.Impl;

import com.group9.server.dao.Interface.IUserAuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

@Component
public class UserAuthenticationDAO extends JdbcDaoSupport implements IUserAuthDao {


    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplateObject;


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public boolean AuthorizeUser(String uname, String pass, String role) {
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(dataSource).withProcedureName("VerifyUserCredentials");
        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("userId", uname)
                .addValue("password", pass)
                .addValue("userrole", role);
        Map results = jdbcCall.execute(inParams);
        return (boolean) results.get("isValid");

    }
}
