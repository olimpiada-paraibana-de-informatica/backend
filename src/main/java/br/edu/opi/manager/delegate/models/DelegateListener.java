package br.edu.opi.manager.delegate.models;

import br.edu.opi.manager.comunication.email.services.SenderMailService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class DelegateListener {

	@Autowired
	SenderMailService senderMailService;

	@PostPersist
	public void methodAfterCreate(Delegate delegate) {
		BeanUtil.autowire(this, this.senderMailService);
		senderMailService.sendVerificationMailToDelegate(delegate);
		senderMailService.sendNewAccountToList(delegate);
	}

}
