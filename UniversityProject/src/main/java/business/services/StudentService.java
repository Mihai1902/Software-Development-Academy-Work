package business.services;

import model.dto.Student;
import model.service.GenericService;

import java.util.List;

public class StudentService {
    GenericService<Student> studentGeneric = new GenericService<>();

    public void addStudent(Student student){
        studentGeneric.add(student);
    }

    public void updateStudent(Student student){
        studentGeneric.update(student);
    }

    public void deleteStudent(Student student){
        studentGeneric.delete(student);
    }

    public Student findStudent(Student student, int id){
        return studentGeneric.findById(student, id);
    }

    public List<Student> getStudents(Student student){
        return studentGeneric.getAll(student);
    }
}