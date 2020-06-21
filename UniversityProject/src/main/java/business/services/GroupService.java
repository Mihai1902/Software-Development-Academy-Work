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

    public Group findGroup(int id){
        return groupGeneric.findById(id);
    }

    public List<Group> getGroups(){
        return groupGeneric.getAll();
    }
}
