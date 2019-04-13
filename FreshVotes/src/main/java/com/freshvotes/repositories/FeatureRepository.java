package com.freshvotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freshvotes.domain.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

}
