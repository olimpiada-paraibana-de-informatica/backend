package br.edu.opi.manager.olympiad.repositories;

import br.edu.opi.manager.olympiad.models.ObiChampion;
import br.edu.opi.manager.olympiad.models.ObiGraphPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObiChampionRepository extends JpaRepository<ObiChampion, ObiGraphPk> {

}
