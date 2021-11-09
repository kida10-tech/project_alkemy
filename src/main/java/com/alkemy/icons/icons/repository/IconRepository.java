package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.IconEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IconRepository extends JpaRepository<IconEntity, Long> {

    List<IconEntity> findAll(Specification<IconEntity> entitySpecification);
}
