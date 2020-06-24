package business.services;

import model.dto.Group;
import model.dto.SubGroup;
import model.service.GenericService;

import java.util.List;

public class SubGroupService {
    GenericService<SubGroup> subGroupGeneric = new GenericService<>();

    public void addSubGroup(SubGroup subGroup){
        subGroupGeneric.add(subGroup);
    }

    public void updateSubGroup(SubGroup subGroup){
        subGroupGeneric.update(subGroup);
    }

    public void deleteSubGroup(SubGroup subGroup){
        subGroupGeneric.delete(subGroup);
    }

    public SubGroup findSubGroup(SubGroup subGroup, int id){
        return subGroupGeneric.findById(subGroup, id);
    }

    public List<SubGroup> getSubGroups(SubGroup subGroup){
        return subGroupGeneric.getAll(subGroup);
    }
}