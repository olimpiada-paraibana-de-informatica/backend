package br.edu.opi.manager.comunication.email.services;

import br.edu.opi.manager.comunication.email.repositories.InterestedNewDelegatesRepository;
import br.edu.opi.manager.delegate.models.Delegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SenderMailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SenderMailService.class.getSimpleName());

	private ExecutorService workers;
	private JavaMailSender mailSender;
	private InterestedNewDelegatesRepository interestedNewDelegatesRepository;

	@Autowired
	public SenderMailService(
			@Value("${mail.workers.maxPoolSize}") final Integer maxPoolSize,
			JavaMailSender mailSender,
			InterestedNewDelegatesRepository interestedNewDelegatesRepository) {
		this.workers = Executors.newFixedThreadPool(maxPoolSize);
		this.mailSender = mailSender;
		this.interestedNewDelegatesRepository = interestedNewDelegatesRepository;
	}

	public void sendVerificationMailToDelegate(Delegate delegate) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(delegate.getUsername());
		message.setSubject("[OPI] Bem vindo à Olimpíada Paraíbana de Informática");
		String text = "Olá " + delegate.getName() + "," +
				"\n\n\tUma conta foi criada em seu nome.\n" +
				"\tPara confirmar seus dados basta clicar no link abaixo:\n" +
				"\t\thttps://opiintern.herokuapp.com/u=asjlkjnlkj3nrlwjw378\n\n" + // TODO: solve link
				"\tSe não foi você quem criou a conta, favor desconsiderar esse e-mail.\n\n" +
				"Att,\n" +
				"Equipe OPI";
		message.setText(text);
		senderEmailAsync(message);
	}

	public void sendNewAccountToList(Delegate delegate) {
		SimpleMailMessage message = new SimpleMailMessage();
		// "rohitgheyi@gmail.com", "raquel.paz@ccc.ufcg.edu.br"
		message.setTo("eri.silva@ccc.ufcg.edu.br"); // TODO: solve with interested list
		message.setSubject("[OPI] Nova conta criada");
		String text = "Olá.\n\n\t" + delegate.getName() +
				" registrou um conta para o e-mail " +
				delegate.getUsername() + ".\n\n" +
				"\tMais detalhes em: https://opiintern.herokuapp.com\n\n" +
				"Att,\n" +
				"Equipe OPI";
		message.setText(text);
		senderEmailAsync(message);
	}

	public void sendNewLevelNotificationToList(String delegateName, String schoolName) {
		SimpleMailMessage message = new SimpleMailMessage();
		// "rohitgheyi@gmail.com", "raquel.paz@ccc.ufcg.edu.br"
		message.setTo("eri.silva@ccc.ufcg.edu.br"); // TODO: solve with interested list
		message.setSubject("[OPI] Novo preenchimento de notas");
		String text = "Olá,\n\n\t" +
				delegateName + " pediu para avisar que já postou as notas de todos os alunos da escola " +
				schoolName + ".\n\n" +
				"Att,\n" +
				"Equipe OPI";
		message.setText(text);
		senderEmailAsync(message);
	}

	private void senderEmailAsync(SimpleMailMessage message) {
		workers.execute(() -> {
			try {
				mailSender.send(message);
			} catch (Exception e) {
				LOGGER.error(e.getStackTrace().toString());
				// TODO: email rec strategy
			}
		});
	}

}
