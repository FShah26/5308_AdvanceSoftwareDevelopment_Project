package com.group9.server.AddUser.ServiceLayer;

import com.group9.server.AddUser.DaoLayer.IAddUserDao;
import com.group9.server.AddUser.ServiceLayer.IAddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddUserService implements IAddUserService {

    @Autowired
    IAddUserDao addUserDao;

    @Override
    public void AddUser(String id,String userid,String password,String user_type) {
        addUserDao.AddUser(id,userid,password,user_type);
    }
}

