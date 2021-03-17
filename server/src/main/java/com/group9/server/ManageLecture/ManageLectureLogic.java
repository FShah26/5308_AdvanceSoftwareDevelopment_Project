package com.group9.server.ManageLecture;

import com.group9.server.Notes.INotesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class ManageLectureLogic implements IManageLectureLogic{

    @Autowired
    IManageLecturePersistence manageLecturePersistence;

    @Autowired
    IDateValidator dateValidator;

    @Autowired
    public ManageLectureLogic(IManageLecturePersistence manageLecturePersistence) {
        this.manageLecturePersistence = manageLecturePersistence;
    }

    @Override
    public List<Lecture> viewLectures(String courseId) {
        return null;
    }

    @Override
    public boolean scheduleLecture(String facultyId,String courseId, String topic, String lecDate) {
        boolean result=false;
        try{
            if(validateCourseId(facultyId,courseId) && dateValidator.validate(lecDate)){
                    Date lectureDate;
                    SimpleDateFormat df =new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    try{
                        Date lectureDt= df.parse(lecDate);
                        if(doesExist(facultyId,courseId,lectureDt)){
                            System.out.println("Lecture is already scheduled");
                            result=false;
                        }
                        else{
                            result=manageLecturePersistence.addLecture(facultyId,courseId,topic,lectureDt);
                        }
                    }
                    catch (ParseException ex){
                        System.out.println("Scheduling lecture failed");
                        ex.getMessage();
                        result=false;
                    }
            }
            result=false;
        } catch(SQLException exception){
            System.out.println("Scheduling lecture failed");
            exception.printStackTrace();
            result=false;
        }
        return result;
    }

    @Override
    public boolean rescheduleLecture() {
        return false;
    }

    @Override
    public boolean cancelLecture() {
        return false;
    }


    public boolean validateCourseId(String facultyId,String courseId){
        ArrayList<String> lstFacultyCourse = new ArrayList<String>();
        try {
            ResultSet set = manageLecturePersistence.getFacultyCourses(facultyId);

            if (set != null) {
                while (set.next()) {
                    lstFacultyCourse.add(set.getString(1));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error validating courseId");
            ex.getMessage();
            return false;
        }
        if(lstFacultyCourse.contains(courseId))
        {
            return true;
        }
        System.out.println("Incorrect Course Id");
        return false;
    }

    public boolean doesExist(String facultyId,String courseId,Date lecDate){
        boolean result=false;
        ArrayList<Lecture> lstFacultyLectures = new ArrayList<Lecture>();
        try{
            ResultSet set = manageLecturePersistence.getAllLectures(facultyId);
            if (set == null) {
                result=false;
            }
            else
            {
                while (set.next()) {
                    Integer lecId=set.getInt(1);
                    String course = set.getString(2);
                    String topic = set.getString(3);
                    Date date=set.getDate(4);
                    Lecture lec = new Lecture(lecId,course,topic,date);
                    lstFacultyLectures.add(lec);
                }
                result=lstFacultyLectures.stream().filter(x -> x.courseId==courseId && x.lectureDate==lecDate).findFirst().isPresent();
            }
        }
        catch (SQLException ex){
            System.out.println("Error validating if course exists");
            result=true;
        }
        return result;
    }

}
