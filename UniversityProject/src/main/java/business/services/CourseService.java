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

    public Course findCourse(Course course, int id){
        return courseGeneric.findById(course, id);
    }

    public List<Course> getCourses(Course course){
        return courseGeneric.getAll(course);
    }

}