package br.edu.opi.manager.office_io.dtos;

import br.edu.opi.manager.olympiad.models.OpiAward;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CompetitorAwardedIO {

	public Set<Long> toAwardedSet(List<CompetitorAwardedInput> input) {
		Set<Long> output = new HashSet<>();
		for (CompetitorAwardedInput cai : input) {
			output.add(cai.getCompetitorId());
		}
		return output;
	}

}
