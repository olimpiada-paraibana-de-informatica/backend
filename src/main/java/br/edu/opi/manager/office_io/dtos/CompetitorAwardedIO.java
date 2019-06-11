package br.edu.opi.manager.office_io.dtos;

import br.edu.opi.manager.olympiad.models.OpiAward;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CompetitorAwardedIO {

	public Map<Long, OpiAward> toAwardedMap(List<CompetitorAwardedInput> input) {
		Map<Long, OpiAward> output = new HashMap<>();
		for (CompetitorAwardedInput cai : input) {
			output.put(cai.getCompetitorId(), OpiAward.from(cai.getAward()));
		}
		return output;
	}

}
