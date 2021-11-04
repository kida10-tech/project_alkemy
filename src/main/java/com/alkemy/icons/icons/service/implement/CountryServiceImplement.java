package com.alkemy.icons.icons.service.implement;


import com.alkemy.icons.icons.dto.CountryDTO;
import com.alkemy.icons.icons.dto.CountryDTOFilter;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.CountryMapper;
import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.repository.CountryRepository;
import com.alkemy.icons.icons.repository.specification.CountrySpecification;
import com.alkemy.icons.icons.service.CountryService;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplement implements CountryService {

    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private IconMapper iconMapper;
    @Autowired
    private IconService iconService;
    @Autowired
    private CountrySpecification countrySpec;


    public CountryDTO save(CountryDTO dto) {
        CountryEntity countryEntity = countryMapper.countryDTOToEntity(dto, true); //Mapeo la entidad a DTO
        CountryEntity countrySaved = countryRepository.save(countryEntity); //Aqui tenemos la entidad guardada con Id, image, y nombre, en forma de entidad.
        // Como el controller maneja DTO debemos convertirla en un DTO.
        CountryDTO result = countryMapper.countryEntityToDTO(countrySaved, false);
        return result;
    }

    public List<CountryDTO> getAllCountries() {
        List<CountryEntity> entities = countryRepository.findAll();
        List<CountryDTO> result = countryMapper.countryEntityListToDTOList(entities, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    @Override
    public CountryDTO modify(Long id, CountryDTO countryDTO) {

        CountryEntity savedCountry = this.getById(id);

        savedCountry.setImage(countryDTO.getImage());
        savedCountry.setName(countryDTO.getName());
        savedCountry.setInhabitantsNumber(countryDTO.getInhabitantsNumber());
        savedCountry.setSurfaceArea(countryDTO.getSurfaceArea());
        savedCountry.setContinentId(countryDTO.getContinentId());
        savedCountry.setIcons(iconMapper.iconDTOList2EntityList(countryDTO.getIcons()));

        CountryEntity countryEntity = countryRepository.save(savedCountry);
        CountryDTO result = countryMapper.countryEntityToDTO(countryEntity, false);

        return result;
    }

    @Override
    public void addIcon(Long countryId, Long iconId) {

        CountryEntity countryEntity = this.getById(countryId);
        countryEntity.getIcons().size();

        IconEntity iconEntity = iconService.getById(iconId);
        countryEntity.addIcon(iconEntity);
        countryRepository.save(countryEntity);
    }

    @Override
    public List<CountryDTO> getByFilters(String name, String continent, String order) {
        CountryDTOFilter countryDTOFilters = new CountryDTOFilter(name, continent, order);
        List<CountryEntity> countryEntities = countryRepository.findAll(countrySpec.getByFilters(countryDTOFilters));
        List<CountryDTO> result = countryMapper.countryEntityListToDTOList(countryEntities, true);

        return result;
    }

    @Override
    public CountryEntity getById(Long id) {
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);
        if(!countryEntity.isPresent()) {
            throw new ParamNotFound("This country does not exist.");
        }
        return countryEntity.get();
    }
}
