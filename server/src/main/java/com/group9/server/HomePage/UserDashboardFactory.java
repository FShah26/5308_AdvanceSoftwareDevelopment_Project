package com.group9.server.HomePage;

import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.FacultyDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.StudentDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDashboardFactory implements IUserDashboardFactory {
    ApplicationContext context;
    Map<String,IDashboard> action;

    public UserDashboardFactory(ApplicationContext context) {
        this.context = context;
        action = new HashMap<>();
        action.put(UserConstants.ADMIN,context.getBean(AdminDashboard.class));
        action.put(UserConstants.FACULTY,context.getBean(FacultyDashboard.class));
        action.put(UserConstants.STUDENT,context.getBean(StudentDashboard.class));
    }

    @Override
    public IDashboard getDashboard(String userType, String userName) {
        IDashboard userDashboard = null;
        userDashboard = action.get(userType);
        userDashboard.setUsername(userName);
        return userDashboard;
    }
}
