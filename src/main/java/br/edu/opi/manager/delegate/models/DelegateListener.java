package br.edu.opi.manager.delegate.models;

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
		// TODO: refactoring e-mail content with CRUD
		String text = "Olá " + delegate.getName() + "," +
				"\n\n\tUma conta foi criada em seu nome.\n" +
				"\tPara confirmar seus dados basta clicar no link abaixo:\n" +
				"\t\thttps://opiintern.herokuapp.com/u=asjlkjnlkj3nrlwjw378\n\n" + // TODO: solve link
				"\tSe não foi você quem criou a conta, favor desconsiderar esse e-mail.\n\n" +
				"Att,\n" +
				"Equipe OPI";
		senderMailService.send(email, subject, text);
		senderMailService.sendToList(delegate);
	}

}
