package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.CountryEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {

    List<CountryEntity> findAll(Specification<CountryEntity> entitySpecification);
}
