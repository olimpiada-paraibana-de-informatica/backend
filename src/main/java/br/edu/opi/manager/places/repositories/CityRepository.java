package br.edu.opi.manager.places.repositories;

import br.edu.opi.manager.places.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findFirst10ByNameContainingIgnoreCaseAndUfAbbreviationIgnoreCase(String namePartial, String statePartial);

	List<City> findFirst10ByNameContainingIgnoreCase(String namePartial);

	List<City> findByUfAbbreviationIgnoreCase(String ufAbbreviation);

	List<City> findByUfCboCode(Long stateCbo);

}
