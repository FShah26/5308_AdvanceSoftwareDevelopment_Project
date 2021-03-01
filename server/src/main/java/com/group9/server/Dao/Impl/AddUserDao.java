package com.group9.server.Dao.Impl;

import com.group9.server.Dao.Interface.IAddUserDao;
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
public class AddUserDao extends JdbcDaoSupport implements IAddUserDao {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplateObject;

    @PostConstruct
    private void initialize(){setDataSource(dataSource);}

    @Override
    public void AddUser(String id,String userid,String password,String user_type) {
        SimpleJdbcCall jdbcCall = new
                SimpleJdbcCall(dataSource).withProcedureName("Create_NewUser");
        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("userid", userid)
                .addValue("password", password)
                .addValue("user_type", user_type);
        System.out.println();
        System.out.println("Please wait.. processing...");
        System.out.println();
        Map results = jdbcCall.execute(inParams);
        String message =(String)results.get("msg");
        System.out.println(message);
    }
}

