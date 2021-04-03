package com.group9.server.HomePage;

import com.group9.server.Dashboard.AdminDashboard;
import com.group9.server.Dashboard.FacultyDashboard;
import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Dashboard.StudentDashboard;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UserDashboardFactory implements IUserDashboardFactory {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HomePageConfiguration.class);

    @Override
    public IDashboard getDashboard(String userType, String userName) {
        IDashboard userDashboard = null;

        switch (userType) {
            case UserConstants.ADMIN:
                userDashboard = ctx.getBean(AdminDashboard.class);
                break;
            case UserConstants.FACULTY:
                userDashboard = ctx.getBean(FacultyDashboard.class);
                break;
            case UserConstants.STUDENT:
                userDashboard = ctx.getBean(StudentDashboard.class);
                break;
        }
        userDashboard.setUsername(userName);
        return userDashboard;
    }
}
