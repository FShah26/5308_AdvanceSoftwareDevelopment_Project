package com.group9.server.services.Implementation;

import com.group9.server.services.Interface.ICourseService;
import com.group9.server.dao.Interface.ICourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseService implements ICourseService {

    @Autowired
    ICourseDao courseDao;

    @Override
    public void Create_Courses(String course_id,String course_name,String course_credit,String course_faculty,String course_Department) {
        courseDao.CreateCourses(course_id,course_name,course_credit,course_faculty,course_Department);
    }
}

