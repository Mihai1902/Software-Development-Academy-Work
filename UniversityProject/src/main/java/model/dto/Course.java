package model.dto;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int courseID;

    @Column(name = "CourseName")
    private String name;

    @Column(name = "Description")
    private String description;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<Group> groups;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<Teacher> teachers;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<Timetable> timetables;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<Timetable> timetables) {
        this.timetables = timetables;
    }
}