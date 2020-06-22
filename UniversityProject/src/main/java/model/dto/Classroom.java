package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "Classrooms")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ClassroomName")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ClassroomID")
    private Timetable timetable;

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Timetable getTimetable() {
        return timetable;
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
}