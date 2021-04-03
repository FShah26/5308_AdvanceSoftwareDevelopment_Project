package com.group9.server.UpcomingLecture;

import com.group9.server.Dashboard.IDashboard;
import com.group9.server.Meeting.StudentRequestMeeting.ICourseSelectionValidator;
import com.group9.server.Meeting.StudentRequestMeeting.IRequestMeetingLogic;
import com.group9.server.Meeting.StudentRequestMeeting.RegisteredCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class UpcomingLectureDisplay implements IUpcomingLectureDisplay {

    @Autowired
    IRequestMeetingLogic meeting;

    @Autowired
    ICourseSelectionValidator courseSelection;

    @Autowired
    IUpcomingLectureLogic lectureLogic;

    @Qualifier("studentDashboard")
    @Autowired
    IDashboard dash;

    RegisteredCourses course;

    Scanner sc=new Scanner(System.in);
    String studentId="";

    @Override
    public void lectureDisplay(String username) throws SQLException {
        studentId = username;
        System.out.println("************************************************");
        System.out.println("              UPCOMING LECTURES                 ");
        System.out.println("************************************************");
        System.out.println("-->Press select the courses to view their upcoming lecture time.");
        checkInput();
    }

    @Override
    public void checkInput() throws SQLException {
        course = meeting.viewCourses(studentId);
        int coursenumber = 0;
        if (course.courseId.size() == 0) {
            System.out.println("Looks like you not enrolled to any courses.");
        }
        else {
            for (String courseid : course.courseId)
            {
                coursenumber++;
                System.out.println("Press " + coursenumber + " for " + courseid);
            }
        }
        System.out.println("Press * to navigate to your dashboard ");
        selectCourse(coursenumber);
    }

    @Override
    public void selectCourse(int number) throws SQLException {
        String courseOption = sc.nextLine();
        if(this.courseSelection.validate(courseOption,number))
        {
            String selected = course.courseId.get(Integer.parseInt(courseOption)-1);
            ArrayList<LectureDetails> details = lectureLogic.upcoming(selected);
            if(details.size()>0)
            {
                System.out.println("________________________________________________________________________________________________________________");
                System.out.printf("%-20s%-15s%-50s%-50s\n", "Faculty ID","Course","Topic","Lecture Date and Time");
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                for(LectureDetails m: details)
                {
                    System.out.printf("%-20s%-15s%-50s%-50s\n", m.facultyid,m.courseid,m.topic,m.date);
                }
            }
            else
            {
                System.out.println("Seems like there is no upcoming lecture for this course as of now.Select different course or navigate back to your dashboard.");
            }
            lectureDisplay(studentId);
        }
        else if(courseOption.equals("*"))
        {
            dash.showDashboard();
        }
        else
        {
            System.out.println("Please select valid option from the above mentioned courses..");
            selectCourse(number);
        }
    }
}
