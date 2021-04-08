package com.group9.server.UpcomingLecture;

import com.group9.server.Meeting.StudentRequestMeeting.IRequestMeetingLogic;
import com.group9.server.Meeting.StudentRequestMeeting.RegisteredCourses;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class UpcomingLectureDisplay implements IUpcomingLectureDisplay {

    IRequestMeetingLogic meeting;
    IChooseCourseValidate chooseCourseValidate;
    IUpcomingLectureLogic lectureLogic;

    RegisteredCourses course;

    Scanner scanner = new Scanner(System.in);
    String studentId = "";

    public UpcomingLectureDisplay(IRequestMeetingLogic meeting, IChooseCourseValidate chooseCourseValidate, IUpcomingLectureLogic lectureLogic) {
        this.meeting = meeting;
        this.chooseCourseValidate = chooseCourseValidate;
        this.lectureLogic = lectureLogic;
    }

    @Override
    public void lectureDisplay(String userName) {
        studentId = userName;
        System.out.println("************************************************");
        System.out.println("              UPCOMING LECTURES                 ");
        System.out.println("************************************************");
        System.out.println("-->Press select the courses to view their upcoming lecture time.");
        checkInput();
    }

    @Override
    public void checkInput() {
        course = meeting.viewCourse(studentId);
        int courseNumber = 0;
        if (course.courseId.size() == 0) {
            System.out.println("Looks like you not enrolled to any courses.");
        } else {
            for (String courseId : course.courseId) {
                courseNumber++;
                System.out.println("Press " + courseNumber + " for " + courseId);
            }
        }
        System.out.println("Press * to navigate to your dashboard ");
        selectCourse(courseNumber);
    }

    @Override
    public void selectCourse(int number) {
        String courseOption = scanner.nextLine();
        if (this.chooseCourseValidate.validate(courseOption, number)) {
            if (Character.isDigit(courseOption.charAt(0))) {
                String selected = course.courseId.get(Integer.parseInt(courseOption) - 1);
                ArrayList<LectureDetails> details = lectureLogic.upcoming(selected);
                if (details.size() > 0) {
                    System.out.println("________________________________________________________________________________________________________________");
                    System.out.printf("%-20s%-15s%-50s%-50s\n", "Faculty ID", "Course", "Topic", "Lecture Date and Time");
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    for (LectureDetails m : details) {
                        System.out.printf("%-20s%-15s%-50s%-50s\n", m.facultyId, m.courseId, m.topic, m.date);
                    }
                } else {
                    System.out.println("Seems like there is no upcoming lecture for this course as of now.Select different course or navigate back to your dashboard.");
                }
                lectureDisplay(studentId);
            } else {
                System.out.println("Back to Dashboard");
            }
        } else {
            System.out.println("Please select valid option from the above mentioned courses..");
            selectCourse(number);
        }
    }

    @Override
    public void execute(String userRole, String userId) {
        lectureDisplay(userId);
    }
}
