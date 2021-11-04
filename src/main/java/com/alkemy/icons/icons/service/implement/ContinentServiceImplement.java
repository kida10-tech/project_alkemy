package com.alkemy.icons.icons.service.implement;

import com.alkemy.icons.icons.dto.ContinentDTO;
import com.alkemy.icons.icons.entity.ContinentEntity;
import com.alkemy.icons.icons.mapper.ContinentMapper;
import com.alkemy.icons.icons.repository.ContinentRepository;
import com.alkemy.icons.icons.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentServiceImplement implements ContinentService {

    @Autowired
    private ContinentMapper continentMapper;
    @Autowired
    private ContinentRepository continentRepository;

    public ContinentDTO save(ContinentDTO dto) {
        ContinentEntity continentEntity = continentMapper.continentDTOToEntity(dto); //Aqui convierto a ContinentDTO a entity.
        ContinentEntity continentSaved = continentRepository.save(continentEntity);
        //Aqui tenemos la entidad guardada con Id, image, y nombre, en forma de entidad.
        // Como el controller maneja DTO debemos convertirla en un DTO.

        ContinentDTO result = continentMapper.continentEntityToDTO(continentSaved); //Finalmente vuelvo a convertir la entidad guardada en DTO.
        return result;
    }


    public List<ContinentDTO> getAllContinents() {
       List<ContinentEntity> entities = continentRepository.findAll();
       List<ContinentDTO> result = continentMapper.continentEntityListToDTOList(entities);
       return result;
    }

}
