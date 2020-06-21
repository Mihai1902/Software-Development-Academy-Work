package business.services;

import model.dto.Course;
import model.service.GenericService;

import java.util.List;

public class CourseService {
    GenericService<Course> courseGeneric = new GenericService<>();

    public void addCourse(Course course){
        courseGeneric.add(course);
    }

    public void updateCourse(Course course){
        courseGeneric.update(course);
    }

    public void deleteCourse(Course course){
        courseGeneric.delete(course);
    }

    public Course findCourse(int id){
        return courseGeneric.findByiD(id);
    }

    public List<Course> getCourses(){
        return courseGeneric.getAll();
    }
}
