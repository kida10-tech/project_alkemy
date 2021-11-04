package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entity.ContinentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentEntity, Long> {
}
