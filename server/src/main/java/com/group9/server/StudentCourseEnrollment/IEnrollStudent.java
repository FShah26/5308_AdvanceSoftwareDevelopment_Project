package com.group9.server.StudentCourseEnrollment;

import com.group9.server.IExecuteAction;

public interface IEnrollStudent extends IExecuteAction {
    void enrollStudent(String userId, String courseId, String term);
    String getUserId();
    String getCourseId();
    String getTerm();
}
