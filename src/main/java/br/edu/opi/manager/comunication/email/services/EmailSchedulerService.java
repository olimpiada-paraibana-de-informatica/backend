package br.edu.opi.manager.comunication.email.services;

import br.edu.opi.manager.delegate.models.Delegate;
import br.edu.opi.manager.delegate.services.DelegateService;
import br.edu.opi.manager.olympiad.models.OpiDates;
import br.edu.opi.manager.olympiad.services.OpiDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@EnableScheduling
public class EmailSchedulerService {

	private static final String ALWAYS_AT_SEVEN_OCLOCK = "0 0 7 \\* \\* \\*";
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final long QUANT_DAYS_BEFORE = 2;

	private final SenderMailService senderMailService;
	private final OpiDatesService opiDatesService;
	private final DelegateService delegateService;

	@Autowired
	public EmailSchedulerService(
			SenderMailService senderMailService,
			OpiDatesService opiDatesService,
			DelegateService delegateService) {
		this.senderMailService = senderMailService;
		this.opiDatesService = opiDatesService;
		this.delegateService = delegateService;
	}

	@Scheduled(cron = ALWAYS_AT_SEVEN_OCLOCK, zone = TIME_ZONE)
	public void sendUpdateEmail() {
		OpiDates opiDates = opiDatesService.show();
		verifyAndSend(opiDates.getSubscribeDate());
		verifyAndSend(opiDates.getIniciacao1And2LevelOneDate());
		verifyAndSend(opiDates.getProgramacaoLevelOneDate());
		verifyAndSend(opiDates.getIniciacao1And2LevelTwoDate());
		verifyAndSend(opiDates.getProgramacaoLevelTwoDate());
		verifyAndSend(opiDates.getResultsDate());
		verifyAndSend(opiDates.getHighSubscribeDate());
		verifyAndSend(opiDates.getHighOlimpiadDate());
		verifyAndSend(opiDates.getHighResultsDate());
		verifyAndSend(opiDates.getAwardDate());
	}

	private void verifyAndSend(String date) {
		try {
			Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
			Matcher matcher = pattern.matcher(date);
			while (matcher.find()) {
				String onlyDate = matcher.group();
				if (isQuantDaysBefore(onlyDate)) {
					sendToDelegates();
				}
			}
		} catch (Exception e) {
			// TODO: scheduler rec strategy
		}
	}

	private boolean isQuantDaysBefore(String onlyDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate eventDate = LocalDate.parse(onlyDate, formatter);
		LocalDate nowPlusQuantDays = LocalDate.now().plusDays(QUANT_DAYS_BEFORE);
		return nowPlusQuantDays.isEqual(eventDate);
	}

	private void sendToDelegates() {
		List<Delegate> delegates = delegateService.index();
		for (Delegate delegate : delegates) {
			senderMailService.sendUpdateEmail(delegate.getUsername());
		}
	}

}
