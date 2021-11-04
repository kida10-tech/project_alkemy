package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.IconDTOBasic;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class IconMapper {

    @Autowired
    private CountryMapper countryMapper;

    public IconEntity iconDTO2Entity(IconDTO iconDTO) {

        IconEntity iconEntity = new IconEntity();

        iconEntity.setImage(iconDTO.getImage());
        iconEntity.setName(iconDTO.getName());

        //cast string to date
        String date = iconDTO.getCreated();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(date, formatter);
        iconEntity.setCreated(transformedDate);

        iconEntity.setHeight(iconDTO.getHeight());
        iconEntity.setHistory(iconDTO.getHistory());

        return iconEntity;
    }

    public IconDTO iconEntity2DTO(IconEntity iconEntity, boolean findCountry) {
        IconDTO dto = new IconDTO();

        dto.setImage(iconEntity.getImage());
        dto.setName(iconEntity.getName());

        //cast date to string
        LocalDate date = iconEntity.getCreated();//1. Get the original format date
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); //2. Convert it to string
        dto.setCreated(formatDate); //3.Set the string to the DTOEntity

        dto.setHeight(iconEntity.getHeight());
        dto.setHistory(iconEntity.getHistory());

        if(findCountry) {
            List<CountryDTO> dtoList = new ArrayList<>();
            for (CountryEntity entity : iconEntity.getCountries()) {
                dtoList.add(countryMapper.countryEntityToDTO(entity, false));
            }
            dto.setCountries(dtoList);
        }
        return dto;
    }

    public List<IconDTO> iconEntityList2DTOList(List<IconEntity> entities, boolean findCountry) {
        List<IconDTO> dtoList = new ArrayList<>();

        for (IconEntity iconEntity: entities) {
            dtoList.add(iconEntity2DTO(iconEntity, findCountry));
        }
        return dtoList;
    }

    public Set<IconEntity> iconDTOList2EntityList(Set<IconDTO> dtoSet) {
        Set<IconEntity> entitySet = new HashSet<>();
        for (IconDTO dto: dtoSet) {
            entitySet.add(iconDTO2Entity(dto));
        }
        return entitySet;
    }

    public List<IconDTOBasic> iconEntityList2DTOBasicList(Collection<IconEntity> entities) {
        List<IconDTOBasic> basicList = new ArrayList<>();
        IconDTOBasic dtoBasic;

        for (IconEntity entity: entities) {
            dtoBasic = new IconDTOBasic();
            dtoBasic.setId(entity.getId());
            dtoBasic.setImage(entity.getImage());
            dtoBasic.setName(entity.getName());
            basicList.add(dtoBasic);
        }
        return basicList;
    }



}
