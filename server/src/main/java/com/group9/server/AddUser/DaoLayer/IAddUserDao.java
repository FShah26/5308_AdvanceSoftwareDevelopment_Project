package com.group9.server.AddUser.DaoLayer;
import javax.sql.DataSource;
import java.util.List;

public interface IAddUserDao {
    void AddUser(String id,String userid,String password,String user_type);
}
