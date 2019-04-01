package br.edu.opi.manager.delegate.model;

import br.edu.opi.manager.comunication.email.SenderMailService;
import br.edu.opi.manager.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class DelegateListener {

	@Autowired
	SenderMailService senderMailService;

	private final String subject = "[OPI] Bem vindo à Olimpíada Paraíbana de Informática";

	@PostPersist
	public void methodAfterCreate(Delegate delegate) {
		BeanUtil.autowire(this, this.senderMailService);
		String email = delegate.getUsername();
		// TODO: refactoring e-mail content
		String text = "\tOlá " + delegate.getName() + "," +
				" uma nova conta, em seu nome, foi criada em nossos registros.\n" +
				"\tPara confirmar seus dados basta clicar no link abaixo:\n" +
				"<a href='http:\\google.com'>Clique aqui!</a>\n\n" +
				"<sub>Se não foi você, favor desconsiderar esse e-mail.</sub>\n\n" +
				"Até breve,\n" +
				"Equipe OPI";
		senderMailService.send(email, subject, text);
		senderMailService.sendToList(delegate);
	}

}
