package br.edu.opi.manager.excel_io.repositories;

import br.edu.opi.manager.excel_io.models.CompetitorTableRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTableRowRepository extends JpaRepository<CompetitorTableRow, Long> {

}
