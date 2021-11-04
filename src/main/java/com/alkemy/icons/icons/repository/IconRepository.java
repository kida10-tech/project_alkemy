package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IconRepository extends JpaRepository<IconEntity, Long> {

    List<IconEntity> findAll(Specification<IconEntity> entitySpecification);
}
