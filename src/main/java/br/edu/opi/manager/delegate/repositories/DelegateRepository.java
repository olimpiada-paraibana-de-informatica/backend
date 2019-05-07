package br.edu.opi.manager.delegate.repositories;

import br.edu.opi.manager.delegate.models.Delegate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>{

	Delegate findByUsername(String username);

}
