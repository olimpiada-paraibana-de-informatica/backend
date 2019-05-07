package br.edu.opi.manager.delegate.models;

// import br.edu.opi.manager.comunication.email.SenderMailService;
// import br.edu.opi.manager.utils.BeanUtil;
// import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PostPersist;

public class DelegateListener {

	// @Autowired
	// SenderMailService senderMailService;

	// private final String subject = "[OPI] Bem vindo à Olimpíada Paraíbana de Informática";

	@PostPersist
	public void methodAfterCreate(Delegate delegate) {
		// BeanUtil.autowire(this, this.senderMailService);
		// String email = delegate.getUsername();
		// // TODO: refactoring e-mail content
		// String text = "\tOlá " + delegate.getName() + "," +
		// 		" uma conta foi criada em seu nome.\n" +
		// 		"\tPara confirmar seus dados basta clicar no link abaixo:\n" +
		// 		"\t\thttp://opi.org/asjlkjnlkj3nrlwjw378\n\n" +
		// 		"Se não foi você, favor desconsiderar esse e-mail.\n\n" +
		// 		"Até breve,\n" +
		// 		"Equipe OPI";
		// senderMailService.send(email, subject, text);
		// senderMailService.sendToList(delegate);
	}

}
