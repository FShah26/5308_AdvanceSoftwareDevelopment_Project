package com.group9.server.Announcements.Admin;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnnouncementLogic implements IAnnouncementLogic {

    IValidateAnnouncementMade validate;
    IAnnouncementPersistence persist;

    public AnnouncementLogic(IValidateAnnouncementMade validate, IAnnouncementPersistence persist) {
        this.validate = validate;
        this.persist = persist;
    }

    @Override
    public String makeAnnouncement(String userRole, String courseId, String message, String userId) {
        String output;
        if (validate.validateAnnouncement(message)) {
            try {
                output = persist.InsertAnnouncement(userRole, courseId, message, userId);
            } catch (Exception ex) {
                output = "Failed to make announcement";
            }
        } else {
            output = "Please enter only valid message with upto 2000 characters...";
        }
        return output;
    }

    public int validateCourseId(String facultyId, String courseId) {
        List<String> lstStudentCourse = new ArrayList<>();
        ResultSet set;
        try {
            set = persist.getFacultyCourses(facultyId);
            if (null == set || set.next() == false) {
                System.out.println("No course assigned to you");
                return -1;
            } else {
                lstStudentCourse.add(set.getString(1));
                while (set.next()) {
                    lstStudentCourse.add(set.getString(1));
                }
            }
            if (lstStudentCourse.contains(courseId)) {
                return 1;
            }

        } catch (SQLException ex) {
            System.out.println("Error validating courseId");
            ex.getMessage();
            return -1;
        }
        System.out.println("Incorrect Course Id");
        return 0;
    }
}
