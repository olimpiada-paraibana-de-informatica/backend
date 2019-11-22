package br.edu.opi.manager.person.repositories;

import br.edu.opi.manager.person.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByFullNameEquals(String fullName);

}
