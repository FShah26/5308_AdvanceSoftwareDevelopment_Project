package com.group9.server.services.Implementation;

import com.group9.server.dao.Interface.IAddUserDao;
import com.group9.server.services.Interface.IAddUserService;
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

