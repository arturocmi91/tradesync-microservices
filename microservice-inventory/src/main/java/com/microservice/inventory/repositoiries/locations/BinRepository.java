package com.microservice.inventory.repositoiries.locations;

import com.microservice.inventory.models.locations.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BinRepository extends JpaRepository<Bin, String> {
}
