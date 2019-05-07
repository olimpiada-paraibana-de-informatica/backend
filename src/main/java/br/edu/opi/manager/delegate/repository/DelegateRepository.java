package br.edu.opi.manager.delegate.repository;

import br.edu.opi.manager.delegate.model.Delegate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>{

	Delegate findByUsername(String username);

}
