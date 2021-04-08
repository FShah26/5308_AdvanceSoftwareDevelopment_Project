package com.group9.server.HomePage;

import com.group9.server.Dashboard.IDashboard;

public interface IUserDashboardFactory {
    IDashboard getDashboard(String userType, String userName);
}
