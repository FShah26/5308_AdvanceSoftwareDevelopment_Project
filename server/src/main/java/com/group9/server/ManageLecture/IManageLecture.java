package com.group9.server.ManageLecture;

import com.group9.server.IExecuteAction;

public interface IManageLecture extends IExecuteAction {
    void showManageLectureMenu(String facultyId);

    void manageLectureAction(String selection);
}
