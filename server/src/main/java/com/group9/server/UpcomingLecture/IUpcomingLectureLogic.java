package com.group9.server.UpcomingLecture;

import java.util.ArrayList;

public interface IUpcomingLectureLogic {
    ArrayList<LectureDetails> upcoming(String studentId);
}
