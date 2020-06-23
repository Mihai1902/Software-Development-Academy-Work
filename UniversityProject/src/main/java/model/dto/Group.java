package model.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Grups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int groupID;

    @Column(name = "GroupName")
    private String name;

    @OneToMany
    @JoinColumn(name="group_id")
    private List<SubGroup> subgroups;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "group_course", catalog = "university",
            joinColumns = {
                    @JoinColumn(name = "groupID", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "courseID", nullable = false, updatable = false)
            })
    private List<Course> courses = new ArrayList<>();


    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubGroup> getSubgroups() {
        return subgroups;
    }

    public void setSubgroups(List<SubGroup> subgroups) {
        this.subgroups = subgroups;
    }
}