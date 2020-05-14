package com.poststone.catalog.database.repository;

import com.poststone.catalog.database.entities.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, UUID>, QuerydslPredicateExecutor<Description> {

    Optional<Description> findByProductIdAndLocale(UUID id, String locale);

    boolean existsByProductIdAndLocale(UUID id, String locale);
}
