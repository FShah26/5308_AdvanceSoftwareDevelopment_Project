package com.group9.server.Course_Creation.ServiceLayer;

import com.group9.server.Course_Creation.DaoLayer.ICourseDao;
import com.group9.server.Course_Creation.ServiceLayer.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CourseService implements ICourseService {

    @Autowired
    ICourseDao courseDao;

    @Override
    public void Create_Courses(String course_id,String course_name,String course_credit,String course_faculty,String course_Department) {
        courseDao.CreateCourses(course_id,course_name,course_credit,course_faculty,course_Department);
    }
}

