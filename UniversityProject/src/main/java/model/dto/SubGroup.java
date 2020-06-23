package model.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subgroups")
public class SubGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int subgroupID;

    @Column(name = "SubgroupName")
    private String name;

    @OneToMany
    @JoinColumn(name="subgroup_id")
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subgroup_id")
    private Group group;

    public int getSubgroupID() {
        return subgroupID;
    }

    public void setSubgroupID(int subgroupID) {
        this.subgroupID = subgroupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}