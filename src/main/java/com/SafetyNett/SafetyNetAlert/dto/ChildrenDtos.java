package com.SafetyNett.SafetyNetAlert.dto;

import com.SafetyNett.SafetyNetAlert.model.Personne;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ChildrenDtos {

    private List<ChildrenDto> childrenDtos;
    private List<PersonneDto> perssonesAssociatedToAChild;

    public ChildrenDtos(List<ChildrenDto> childrenDtos, List<PersonneDto> perssonesAssociatedToAChild) {
        this.childrenDtos = childrenDtos;
        this.perssonesAssociatedToAChild = perssonesAssociatedToAChild;
    }
}