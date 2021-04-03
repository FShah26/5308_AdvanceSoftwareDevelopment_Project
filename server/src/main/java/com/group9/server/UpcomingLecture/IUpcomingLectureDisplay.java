package com.group9.server.UpcomingLecture;

import java.sql.SQLException;

public interface IUpcomingLectureDisplay {

     void lectureDisplay(String userName) ;
     void checkInput() ;
     void selectCourse(int number) ;
}
