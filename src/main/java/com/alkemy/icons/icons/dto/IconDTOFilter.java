package com.alkemy.icons.icons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IconDTOFilter {

    private String name;
    private String date;
    private Set<Long> cities;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0 ;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0 ;
    }
}
