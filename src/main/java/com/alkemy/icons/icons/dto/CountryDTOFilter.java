package com.alkemy.icons.icons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTOFilter {

    private String name;
    private String continent;
    private String order;

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0 ;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0 ;
    }
}
