package br.edu.opi.manager.comunication.notification.models;

import br.edu.opi.manager.comunication.email.services.SenderMailService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class DelegateLevelNotificationListener {

	@Autowired
	SenderMailService senderMailService;

	@PostPersist
	public void methodAfterCreate(DelegateLevelNotification notification) {
		BeanUtil.autowire(this, this.senderMailService);
		String delegateName = notification.getDelegate().getName();
		String schoolName = notification.getSchoolName();
		senderMailService.sendNewLevelNotificationToList(delegateName, schoolName);
	}

}
