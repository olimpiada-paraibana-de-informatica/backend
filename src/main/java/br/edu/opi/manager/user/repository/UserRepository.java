package br.edu.opi.manager.user.repository;

import br.edu.opi.manager.user.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Persistence layer to Simple User.
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

	Set<UserModel> findByUsernameContaining(String username);

	UserModel findByUsername(String username);

	List<UserModel> findAllByProfileId(Long profileId);

	List<UserModel> findFirst10ByUsernameContainingIgnoreCase(String value, Sort sort);

}