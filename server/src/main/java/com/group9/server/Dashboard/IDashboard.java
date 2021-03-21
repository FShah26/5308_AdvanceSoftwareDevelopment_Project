package com.group9.server.Dashboard;

import java.sql.SQLException;

public interface IDashboard {
    void dashboard() throws SQLException;

    void setUsername(String username);
}
