package br.edu.opi.manager.comunication.notification.repositories;

import br.edu.opi.manager.comunication.notification.models.DelegateLevelNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegateLevelNotificationRepository extends JpaRepository<DelegateLevelNotification, Long> {

	DelegateLevelNotification findAllByDelegateIdAndYear(Long delegateId, Integer year);

}
