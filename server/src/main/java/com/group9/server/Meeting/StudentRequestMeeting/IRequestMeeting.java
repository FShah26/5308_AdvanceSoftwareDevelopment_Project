package com.group9.server.Meeting.StudentRequestMeeting;

import com.group9.server.IExecuteAction;

public interface IRequestMeeting extends IExecuteAction {
    void meetingDisplay(String username);

    void selectMenu();

    void checkInput(String selection);

    void manageMeetingAction(String selection);

    void selectCourse(int select);
}
