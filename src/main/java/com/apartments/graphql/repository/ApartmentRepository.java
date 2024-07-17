package com.apartments.graphql.repository;

import com.apartments.graphql.domain.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, String>, CrudRepository<ApartmentEntity, String> {
}
