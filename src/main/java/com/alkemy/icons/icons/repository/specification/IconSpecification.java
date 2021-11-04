package com.alkemy.icons.icons.repository.specification;

import com.alkemy.icons.icons.dto.IconDTOFilter;
import com.alkemy.icons.icons.entity.CountryEntity;
import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class IconSpecification {

    public Specification<IconEntity> getByFilters(IconDTOFilter filterDTO){

        //Lambda function
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            //Creating a dynamic query, hasLength() checks if it exist.
            if(StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filterDTO.getName().toLowerCase() + "%"));
            }


            //Date
            if(StringUtils.hasLength(filterDTO.getDate())) {

                //String to LocalDate
                String dateToParse = filterDTO.getDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate date = LocalDate.parse(dateToParse, formatter);

                predicates.add(criteriaBuilder.equal(root.<LocalDate>get("created"),date));
            }


            if(!CollectionUtils.isEmpty(filterDTO.getCities())) { //if CountryId not empty
                Join<CountryEntity, IconEntity> toBeJoined = root.join("countries", JoinType.INNER);
                Expression<String> paisesId = toBeJoined.get("id");

                predicates.add(paisesId.in(filterDTO.getCities()));
            }

            //Remove duplicates
            query.distinct(true);

            //Order by filtering
            String orderByField = "name";
            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0])); // Return the generated predicate as a list
        };
    }
}
