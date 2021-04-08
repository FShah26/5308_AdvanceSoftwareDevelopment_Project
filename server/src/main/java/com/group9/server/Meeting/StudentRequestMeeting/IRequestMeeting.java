package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.IExecuteAction;

public interface IRequestMeeting extends IExecuteAction {
    void meetingDisplay(String username);

    void selectMenu();

    void checkInput(String selection);

    void manageMeetingAction(String selection);

    void raiseMeeting(String Student);

    void viewMeeting(String Student);

    void selectCourse(int select);
}
