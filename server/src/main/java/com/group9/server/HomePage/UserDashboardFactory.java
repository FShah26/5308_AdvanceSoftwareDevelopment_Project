package com.group9.server.HomePage;

import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.FacultyDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.StudentDashboard;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserDashboardFactory implements IUserDashboardFactory {
    ApplicationContext context;

    public UserDashboardFactory(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public IDashboard getDashboard(String userType, String userName) {
        IDashboard userDashboard = null;

        switch (userType) {
            case UserConstants.ADMIN:
                userDashboard = context.getBean(AdminDashboard.class);
                break;
            case UserConstants.FACULTY:
                userDashboard = context.getBean(FacultyDashboard.class);
                break;
            case UserConstants.STUDENT:
                userDashboard = context.getBean(StudentDashboard.class);
                break;
        }
        userDashboard.setUsername(userName);
        return userDashboard;
    }
}
