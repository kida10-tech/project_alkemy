package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.entity.CountryEntity;

import java.util.List;

public interface CountryService {

    CountryDTO save(CountryDTO country); //Save the country

    List<CountryDTO> getAllCountries(); //List all the countries

    public void delete(Long id);

    public CountryDTO modify(Long id, CountryDTO countryDTO);

    public void addIcon(Long countryId, Long iconId);

    public List<CountryDTO> getByFilters(String name, String continent, String order);

    public CountryEntity getById(Long id);
}
