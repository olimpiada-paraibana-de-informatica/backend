package br.edu.opi.manager.project_patterns.repositories;

import br.edu.opi.manager.project_patterns.models.user.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Persistence layer to Profile.
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile findByName(String name);

	@Override
	void delete(Profile profile);

	List<Profile> findFirst10ByNameContainingIgnoreCase(String value, Sort sort);

}