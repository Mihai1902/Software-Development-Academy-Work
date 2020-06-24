package business.services;

import model.dto.Classroom;
import model.service.GenericService;

import java.util.List;

public class ClassroomService {
    GenericService<Classroom> classroomGeneric = new GenericService<>();

    public void addClassroom(Classroom classroom){
        classroomGeneric.add(classroom);
    }

    public void updateClassroom(Classroom classroom){
        classroomGeneric.update(classroom);
    }

    public void deleteClassroom(Classroom classroom){
        classroomGeneric.delete(classroom);
    }

    public Classroom findClassroom(Classroom classroom, int id){
        return classroomGeneric.findById(classroom, id);
    }

    public List<Classroom> getClassrooms(Classroom classroom){
        return classroomGeneric.getAll(classroom);
    }
}