package com.group9.server.Announcements.Admin;

import java.sql.SQLException;

public interface IAnnouncementInput {

    void make_announcement(String userRole,String userId) throws SQLException;
    void select_option() throws SQLException;
}
