package com.microservice.inventory.repositoiries.locations;

import com.microservice.inventory.models.locations.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/*public interface LocationRepository <T extends Location> extends JpaRepository<T, String>{
}*/ //CASO GENERICO PARA HACER PERSISTENCIA CON LA DB CON LOS HIJOS INCLUIDOS

public interface LocationRepository  extends JpaRepository<Location, String>{
}
