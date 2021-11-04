package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.ContinentDTO;

import java.util.List;

public interface ContinentService {

    ContinentDTO save(ContinentDTO dto);

    List<ContinentDTO> getAllContinents();
}
