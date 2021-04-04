package com.group9.server.StudentCourseEnrollment;

import com.group9.server.IExecuteAction;

import java.sql.SQLException;

public interface IEnrollStudent extends IExecuteAction {
    void creation() throws SQLException;
}
