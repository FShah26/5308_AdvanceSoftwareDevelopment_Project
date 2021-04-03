package com.group9.server.Announcements.Admin;

import java.sql.SQLException;

public interface IAnnouncementInput {

    void announcement(String userRole, String userId);

    void selectOption() ;
}
