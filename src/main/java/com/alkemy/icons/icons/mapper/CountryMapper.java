package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.ContinentDTO;
import com.alkemy.icons.icons.dto.CountryDTO;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.entity.ContinentEntity;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CountryMapper {

    @Autowired
    private IconMapper iconMapper;

    public CountryEntity countryDTOToEntity(CountryDTO dto, boolean findIcon) {
        CountryEntity countryEntity = new CountryEntity();

        countryEntity.setImage(dto.getImage());
        countryEntity.setName(dto.getName());
        countryEntity.setInhabitantsNumber(dto.getInhabitantsNumber());
        countryEntity.setSurfaceArea(dto.getSurfaceArea());
        countryEntity.setContinentId(dto.getContinentId());

        if(findIcon) {
            Set<IconEntity> entitySet = new HashSet<>();
            for (IconDTO iconDTO: dto.getIcons()) {
                entitySet.add(iconMapper.iconDTO2Entity(iconDTO));
            }
            countryEntity.setIcons(entitySet);
        }
        return countryEntity;
    }

    public CountryDTO countryEntityToDTO(CountryEntity countryEntity, boolean findIcon) {
        CountryDTO dto = new CountryDTO();

        dto.setId(countryEntity.getId());
        dto.setImage(countryEntity.getImage());
        dto.setName(countryEntity.getName());
        dto.setInhabitantsNumber(countryEntity.getInhabitantsNumber());
        dto.setSurfaceArea(countryEntity.getSurfaceArea());
        dto.setContinentId(countryEntity.getContinentId());

        if(findIcon) {
            Set<IconDTO> dtoSet = new HashSet<>();
            for (IconEntity icon: countryEntity.getIcons()) {
                dtoSet.add(iconMapper.iconEntity2DTO(icon, false));
            }
            dto.setIcons(dtoSet);
        }
        return dto;
    }

    //Entity List to DTO List
    public List<CountryDTO> countryEntityListToDTOList(List<CountryEntity> entities, boolean load) {
        List<CountryDTO> dtos = new ArrayList<>();

        for (CountryEntity entity: entities) {
            dtos.add(this.countryEntityToDTO(entity, load));
        }
        return dtos;
    }

    public List<CountryEntity> countryDTOList2EntityList(List<CountryDTO> dtos, boolean load) {
        List<CountryEntity> entities = new ArrayList<>();

        for (CountryDTO dto: dtos) {
            entities.add(countryDTOToEntity(dto, load));
        }
        return entities;
    }
}
