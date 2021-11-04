package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.entity.IconEntity;

import java.util.List;
import java.util.Set;

public interface IconService {

    public List<IconDTO> getAll();

    public IconDTO modify(Long id, IconDTO iconDTO);

    public IconDTO save(IconDTO iconDTO);

    public IconEntity getById(Long id);

    public void delete(Long id);

    public List<IconDTO> getByFilters(String name, String date, Set<Long> countries, String order);

    public IconDTO getDetailById(Long id);

    public void addCountry(Long id, Long idCountry);

    public void removeCountry(Long id, Long idCountry);
}
