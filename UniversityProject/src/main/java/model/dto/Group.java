package model.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "Group Name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Subgroup ID")
    private List<SubGroup> subGroups;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="groups_courses", catalog = "university",
            joinColumns = {
                    @JoinColumn(name="id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="id", nullable = false, updatable = false)
            })
    private List<Course> courses = new ArrayList<>();

    public void setSubGroups(List<SubGroup> subGroups) {
        this.subGroups = subGroups;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<SubGroup> getSubGroups() {
        return subGroups;
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