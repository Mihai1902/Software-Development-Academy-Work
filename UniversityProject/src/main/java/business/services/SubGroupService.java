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

    public SubGroup findSubGroup(int id){
        return subGroupGeneric.findById(id);
    }

    public List<SubGroup> getSubGroups(){
        return subGroupGeneric.getAll();
    }
}
