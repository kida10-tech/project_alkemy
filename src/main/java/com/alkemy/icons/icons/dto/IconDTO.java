package com.alkemy.icons.icons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class IconDTO {

    private Long id;
    private String image;
    private String name;
    private String created;
    private Long height;
    private String history;
    private List<CountryDTO> countries;
}
