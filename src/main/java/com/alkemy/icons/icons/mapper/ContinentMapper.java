package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.ContinentDTO;
import com.alkemy.icons.icons.entity.ContinentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinentMapper {

    public ContinentEntity continentDTOToEntity(ContinentDTO dto) {
        ContinentEntity continentEntity = new ContinentEntity();
        continentEntity.setImage(dto.getImage());
        continentEntity.setName(dto.getName());
        return continentEntity;
    }

    public ContinentDTO continentEntityToDTO(ContinentEntity continentEntity) {
        ContinentDTO dto = new ContinentDTO();
        dto.setId(continentEntity.getId());
        dto.setImage(continentEntity.getImage());
        dto.setName(continentEntity.getName());
        return dto;
    }

    public List<ContinentDTO> continentEntityListToDTOList(List<ContinentEntity> entities) {
        List<ContinentDTO> dtos = new ArrayList<>();

        for (ContinentEntity entity: entities) {
            dtos.add(this.continentEntityToDTO(entity));
        }
        return dtos;
    }
}
