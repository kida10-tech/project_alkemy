package com.alkemy.icons.icons.service.implement;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.IconDTOFilter;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.CountryMapper;
import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.repository.IconRepository;
import com.alkemy.icons.icons.repository.specification.IconSpecification;
import com.alkemy.icons.icons.service.CountryService;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IconServiceImplement implements IconService {

    @Autowired
    private IconMapper iconMapper;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private IconSpecification iconSpecification;
    @Autowired
    private IconRepository iconRepository;
    @Autowired
    private CountryService countryService;


    @Override
    public List<IconDTO> getAll() {
        List<IconEntity> iconEntities = iconRepository.findAll();
        System.out.println("Aca estan los icons" + iconEntities);
        List<IconDTO> dtoList = iconMapper.iconEntityList2DTOList(iconEntities, true);
        return dtoList;
    }

    @Override
    public IconDTO modify(Long id, IconDTO iconDTO) {
        IconEntity savedIcon = this.getById(id);

        String dateDTO = iconDTO.getCreated().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(dateDTO, formatter);

        savedIcon.setImage(iconDTO.getName());
        savedIcon.setName(iconDTO.getName());
        savedIcon.setCreated(transformedDate);
        savedIcon.setHeight(iconDTO.getHeight());
        savedIcon.setHistory(iconDTO.getHistory());
        savedIcon.setCountries(countryMapper.countryDTOList2EntityList(iconDTO.getCountries(), false));

        IconEntity editedIcon = iconRepository.save(savedIcon);

        IconDTO savedDTO = iconMapper.iconEntity2DTO(editedIcon, false);

        return savedDTO;
    }

    @Override
    public IconDTO save(IconDTO iconDTO) {
        IconEntity iconEntity = new IconEntity();

        iconEntity.setImage(iconDTO.getImage());
        iconEntity.setName(iconDTO.getName());

        // Cast String to Date:
        String dateDTO = iconDTO.getCreated();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(dateDTO, formatter);
        iconEntity.setCreated(transformedDate);

        iconEntity.setHeight(iconDTO.getHeight());
        iconEntity.setHistory(iconDTO.getHistory());

        IconEntity savedIcon = iconRepository.save(iconEntity);
        IconDTO savedDTO = iconMapper.iconEntity2DTO(savedIcon, false);


        return savedDTO;
    }

    @Override
    public IconEntity getById(Long id) {
        Optional<IconEntity> icon = iconRepository.findById(id);
        if(!icon.isPresent()) {
            throw new ParamNotFound("This icon does not exist.");
        }
        return icon.get();
    }

    @Override
    public void delete(Long id) {
        iconRepository.deleteById(id);
    }

    @Override
    public List<IconDTO> getByFilters(String name, String date, Set<Long> cities, String order) {
       IconDTOFilter iconDTOFilter = new IconDTOFilter(name, date, cities, order);
       List<IconEntity> entities = iconRepository.findAll(iconSpecification.getByFilters(iconDTOFilter));
       List<IconDTO> result = iconMapper.iconEntityList2DTOList(entities,true);

       return result;
    }

    @Override
    public IconDTO getDetailById(Long id) {
        IconEntity iconEntity = this.getById(id);
        IconDTO result = iconMapper.iconEntity2DTO(iconEntity, true);
        return result;
    }

    public void addCountry(Long id, Long idCountry) {
        IconEntity iconEntity = iconRepository.getById(id);
        iconEntity.getCountries().size();
        CountryEntity country = this.countryService.getById(idCountry);
        iconEntity.addCountry(country);
        this.iconRepository.save(iconEntity);
    }

    public void removeCountry(Long id, Long idCountry) {
        IconEntity iconEntity = iconRepository.getById(id);
        iconEntity.getCountries().size();
        CountryEntity country = this.countryService.getById(idCountry);
        iconEntity.removeCountry(country);
        this.iconRepository.save(iconEntity);
    }
}
