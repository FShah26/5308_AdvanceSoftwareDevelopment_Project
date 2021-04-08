package com.group9.server.UpcomingLecture;

import com.group9.server.IExecuteAction;

public interface IUpcomingLectureDisplay extends IExecuteAction {

    void lectureDisplay(String userName);

    void checkInput();

    void selectCourse(int number);
}
