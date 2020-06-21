package model.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Course Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    private List<Student> students = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    private List<Teacher> teachers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    private List<Group> groups = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "courses")
    private List<Timetable> timetables = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}