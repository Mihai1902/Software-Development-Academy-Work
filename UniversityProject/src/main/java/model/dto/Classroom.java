package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "Classrooms")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int classroomID;

    @Column(name = "ClassroomName")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public int getClassroomID() {
        return classroomID;
    }

    public void setClassroomID(int classroomID) {
        this.classroomID = classroomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}