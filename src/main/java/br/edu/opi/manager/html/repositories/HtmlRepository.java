package br.edu.opi.manager.html.repositories;

import br.edu.opi.manager.html.models.Html;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlRepository extends JpaRepository<Html, String> {

}
