package com.group9.server.Announcements;

import java.sql.SQLException;

public interface IAnnouncementInput {

    void make_announcement(String user_role) throws SQLException;
    void select_option() throws SQLException;
}
