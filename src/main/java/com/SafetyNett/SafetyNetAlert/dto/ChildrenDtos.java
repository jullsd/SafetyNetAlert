package com.SafetyNett.SafetyNetAlert.dto;

import com.SafetyNett.SafetyNetAlert.model.Personne;
import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;
@Data
@Generated
public class ChildrenDtos {

    private List<ChildrenDto> childrenDtos;
    private List<PersonneDto> perssonesAssociatedToAChild;

    public ChildrenDtos(List<ChildrenDto> childrenDtos, List<PersonneDto> perssonesAssociatedToAChild) {
        this.childrenDtos = childrenDtos;
        this.perssonesAssociatedToAChild = perssonesAssociatedToAChild;
    }
}
