package com.SafetyNett.SafetyNetAlert.dto;

import lombok.Generated;;
import java.util.ArrayList;
import java.util.List;

@Generated
public class ChildrenDtos {


    private List<ChildrenDto> childrenDtos;
    private List<PersonneDto> perssonesAssociatedToAChild;

    public List<ChildrenDto> getChildrenDtos() {
        return childrenDtos = new ArrayList<>(childrenDtos);
    }

    public void setChildrenDtos(List<ChildrenDto> childrenDtos) {
        this.childrenDtos = new ArrayList<>(childrenDtos);
    }

    public List<PersonneDto> getPerssonesAssociatedToAChild() {
        return perssonesAssociatedToAChild = new ArrayList<>(perssonesAssociatedToAChild);
    }

    public void setPerssonesAssociatedToAChild(List<PersonneDto> perssonesAssociatedToAChild) {
        this.perssonesAssociatedToAChild = new ArrayList<>(perssonesAssociatedToAChild);
    }


    public ChildrenDtos(List<ChildrenDto> childrenDtos, List<PersonneDto> perssonesAssociatedToAChild) {
        this.childrenDtos = new ArrayList<>(childrenDtos);
        this.perssonesAssociatedToAChild = new ArrayList<>(perssonesAssociatedToAChild);
    }


}
