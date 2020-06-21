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

    public Classroom findClassroom(int id){
        return classroomGeneric.findById(id);
    }

    public List<Classroom> getClassrooms(){
        return classroomGeneric.getAll();
    }
}
