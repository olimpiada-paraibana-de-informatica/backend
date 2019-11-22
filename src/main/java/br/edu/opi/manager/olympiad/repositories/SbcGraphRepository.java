package br.edu.opi.manager.olympiad.repositories;

import br.edu.opi.manager.olympiad.models.SbcGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SbcGraphRepository extends JpaRepository<SbcGraph, Integer> {

}
