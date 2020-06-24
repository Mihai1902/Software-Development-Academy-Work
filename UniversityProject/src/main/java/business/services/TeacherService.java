package business.services;

import model.dto.Teacher;
import model.service.GenericService;

import java.util.List;

public class TeacherService {
    GenericService<Teacher> teacherGeneric = new GenericService<>();

    public void addTeacher(Teacher teacher){
        teacherGeneric.add(teacher);
    }

    public void updateTeacher(Teacher teacher){
        teacherGeneric.update(teacher);
    }

    public void deleteTeacher(Teacher teacher){
        teacherGeneric.delete(teacher);
    }

    public Teacher findTeacher(Teacher teacher, int id){
        return teacherGeneric.findById(teacher, id);
    }

    public List<Teacher> getTeachers(Teacher teacher){
        return teacherGeneric.getAll(teacher);
    }
}