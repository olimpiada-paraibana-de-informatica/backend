package br.edu.opi.manager.comunication.notification.services;

import br.edu.opi.manager.comunication.notification.models.DelegateLevelNotification;
import br.edu.opi.manager.comunication.notification.repositories.DelegateLevelNotificationRepository;
import br.edu.opi.manager.delegate.models.Delegate;
import br.edu.opi.manager.delegate.services.DelegateService;
import br.edu.opi.manager.olympiad.models.OpiLevels;
import br.edu.opi.manager.project_patterns.services.GenericService;
import br.edu.opi.manager.school.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DelegateLevelNotificationService extends GenericService<Long, DelegateLevelNotification, DelegateLevelNotificationRepository> {

	private DelegateService delegateService;

	private SchoolRepository schoolRepository;

	@Autowired
	public DelegateLevelNotificationService(
			DelegateService delegateService,
			SchoolRepository schoolRepository) {
		this.delegateService = delegateService;
		this.schoolRepository = schoolRepository;
	}

	public DelegateLevelNotification create(OpiLevels level, String delegatePrincipal) {
		Delegate delegate = delegateService.show(delegatePrincipal);
		String schoolName = schoolRepository.findSchoolNameByDelegateId(delegate.getId());
		Integer year = LocalDateTime.now().getYear();
		Boolean levelOne = level.equals(OpiLevels.ONE) ? true : null;
		Boolean levelTwo = level.equals(OpiLevels.TWO) ? true : null;
		DelegateLevelNotification notification = new DelegateLevelNotification(delegate, year, levelOne, levelTwo, schoolName);
		return this.create(notification);
	}

	@Override
	public void validateBeforeCreate(DelegateLevelNotification notification) {
		DelegateLevelNotification savedNotification = repository.findAllByDelegateIdAndYear(notification.getDelegate().getId(), notification.getYear());
		if (savedNotification != null) {
			notification.setId(savedNotification.getId());
			if (notification.getLevelOne() == null) {
				notification.setLevelOne(savedNotification.getLevelOne());
			}
			if (notification.getLevelTwo() == null) {
				notification.setLevelTwo(savedNotification.getLevelTwo());
			}
		}
	}

	@Override
	public void validateBeforeUpdate(DelegateLevelNotification notification) {
	}

	@Override
	public void validateBeforeDelete(Long id) {
	}

}
