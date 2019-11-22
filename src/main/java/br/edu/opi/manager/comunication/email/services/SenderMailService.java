package br.edu.opi.manager.comunication.email.services;

import br.edu.opi.manager.comunication.email.repositories.InterestedNewDelegatesRepository;
import br.edu.opi.manager.delegate.models.Delegate;
import com.ecwid.maleorang.MailchimpClient;
import com.ecwid.maleorang.MailchimpObject;
import com.ecwid.maleorang.method.v3_0.lists.members.EditMemberMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Component @EnableScheduling
public class SenderMailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SenderMailService.class.getSimpleName());
	private static final String PROPERTY_EMAIL_ADDRESS = "email_address";
	private static final String PROPERTY_STATUS = "status";
	private static final String VALUE_SUBSCRIBED = "subscribed";
	private static final String PROPERTY_MERGE_FIELDS = "merge_fields";
	private static final String PROPERTY_LANGUAGE = "language";
	private static final String VALUE_PT = "pt";
	private static final String SEND_EMAIL_ERROR = "Erro desconhecido ao enviar e-mails";
	private static final String MERGE_FIELD_FIRST_NAME = "FNAME";
	private static final String MERGE_FIELD_LASTNAME = "LNAME";
	private static final String MERGE_FIELD_EMAIL = "EMAIL";
	private static final String MERGE_FIELD_PHONE = "PHONE";
	private static final String AUTH_BASIC = "Basic YW55c3RyaW5nOmIyZTQ1Y2UwYjVkOGY4NzFjNmFiYmIwZjQwYTA4MGY0LXVzNQ==";

	private Client client = ClientBuilder.newBuilder().build();

	private static final String PATH_LISTS = "lists";
	private static final String PATH_MEMBERS = "members";
	private final String LIST_ID;

	private InterestedNewDelegatesRepository interestedNewDelegatesRepository;

	private final String mailchimpRootUri;
	private final String mailchimpUsername;
	private final String mailchimpApikey;
	private final String mailchimpWorkflowId;
	private final String mailchimpWorkflowEmailId;

	@Autowired
	public SenderMailService(
			@Value("${mail.chimp.rootUri}") final String mailchimpRootUri,
			@Value("${mail.chimp.username}") final String mailchimpUsername,
			@Value("${mail.chimp.password}") final String mailchimpApikey,
			@Value("${mail.chimp.listId}") final String listId,
			@Value("${mail.chimp.workflow_id}") final String mailchimpWorkflowId,
			@Value("${mail.chimp.workflow_email_id}") final String mailchimpWorkflowEmailId,
			InterestedNewDelegatesRepository interestedNewDelegatesRepository
	) {
		this.interestedNewDelegatesRepository = interestedNewDelegatesRepository;
		this.mailchimpRootUri = mailchimpRootUri;
		this.mailchimpUsername = mailchimpUsername;
		this.mailchimpApikey = mailchimpApikey;
		this.LIST_ID = listId;
		this.mailchimpWorkflowId = mailchimpWorkflowId;
		this.mailchimpWorkflowEmailId = mailchimpWorkflowEmailId;
	}

	public void sendWelcomeMailToDelegate(Delegate delegate) {
		try {
			MailchimpClient client = new MailchimpClient(this.mailchimpApikey);
			EditMemberMethod.CreateOrUpdate method = new EditMemberMethod.CreateOrUpdate(this.LIST_ID, delegate.getUsername());
			method.status = VALUE_SUBSCRIBED;
			method.merge_fields = new MailchimpObject();
			method.merge_fields.mapping.put(MERGE_FIELD_FIRST_NAME, delegate.getName());
			method.merge_fields.mapping.put(MERGE_FIELD_LASTNAME, delegate.getName());
			method.merge_fields.mapping.put(MERGE_FIELD_EMAIL, delegate.getUsername());
			method.merge_fields.mapping.put(MERGE_FIELD_PHONE, delegate.getPhone());
			client.execute(method);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			// TODO: email rec strategy
		}
	}

	public void sendUpdateEmail(String email) {
		try {
			MailchimpClient client = new MailchimpClient(this.mailchimpApikey);
			WorkFlowEmailRequest method = new WorkFlowEmailRequest(this.mailchimpWorkflowId, this.mailchimpWorkflowEmailId, email);
			client.execute(method);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			// TODO: email rec strategy
		}
	}

	public void sendNewAccountToList(Delegate delegate) {
		// SimpleMailMessage message = new SimpleMailMessage();
		// // "rohitgheyi@gmail.com", "raquel.paz@ccc.ufcg.edu.br"
		// message.setTo("eri.silva@ccc.ufcg.edu.br"); // TODO: solve with interested list
		// message.setSubject("[OPI] Nova conta criada");
		// String text = "Olá.\n\n\t" + delegate.getName() +
		// 		" registrou um conta para o e-mail " +
		// 		delegate.getUsername() + ".\n\n" +
		// 		"\tMais detalhes em: https://opiintern.herokuapp.com\n\n" +
		// 		"Att,\n" +
		// 		"Equipe OPI";
		// message.setText(text);
		// senderEmailAsync(message);
	}

	public void sendNewLevelNotificationToList(String delegateName, String schoolName) {
		// SimpleMailMessage message = new SimpleMailMessage();
		// // "rohitgheyi@gmail.com", "raquel.paz@ccc.ufcg.edu.br"
		// message.setTo("eri.silva@ccc.ufcg.edu.br"); // TODO: solve with interested list
		// message.setSubject("[OPI] Novo preenchimento de notas");
		// String text = "Olá,\n\n\t" +
		// 		delegateName + " pediu para avisar que já postou as notas de todos os alunos da escola " +
		// 		schoolName + ".\n\n" +
		// 		"Att,\n" +
		// 		"Equipe OPI";
		// message.setText(text);
		// senderEmailAsync(message);
	}

}
