package business.services;

import model.dto.Group;
import model.service.GenericService;

import java.util.List;

public class GroupService {
    GenericService<Group> groupGeneric = new GenericService<>();

    public void addGroup(Group group){
        groupGeneric.add(group);
    }

    public void updateGroup(Group group){
        groupGeneric.update(group);
    }

    public void deleteGroup(Group group){
        groupGeneric.delete(group);
    }

    public Group findGroup(Group group, int id){
        return groupGeneric.findById(group, id);
    }

    public List<Group> getGroups(Group group){
        return groupGeneric.getAll(group);
    }
}