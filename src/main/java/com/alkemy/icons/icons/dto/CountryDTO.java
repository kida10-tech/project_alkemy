package com.alkemy.icons.icons.dto;

import com.alkemy.icons.icons.entity.IconEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CountryDTO {

    private Long id;
    private String image;
    private String name;
    private Long inhabitantsNumber;
    private Long surfaceArea;
    private Long continentId;

    private Set<IconDTO> icons;
}
