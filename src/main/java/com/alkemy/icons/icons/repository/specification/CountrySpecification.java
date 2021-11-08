package com.alkemy.icons.icons.repository.specification;

import com.alkemy.icons.icons.dto.CountryDTOFilter;
import com.alkemy.icons.icons.entity.CountryEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CountrySpecification {

    public Specification<CountryEntity> getByFilters(CountryDTOFilter filterDTO){

        return (root, query, criteriaBuilder)-> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filterDTO.getName().toLowerCase() + "%")
                );
            }

            //If Continent hasLength(if it exist).
            if(StringUtils.hasLength(filterDTO.getContinent())) {
                Long myId = Long.parseLong(filterDTO.getContinent());
                System.out.println(myId);
                predicates.add(
                        criteriaBuilder.equal(root.get("continent_id"), myId)
                );
            }
            query.distinct(true);

            String orderByField = "name"; //Order by ASC
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0])); //Return predicate as a list

        };
    }
}
