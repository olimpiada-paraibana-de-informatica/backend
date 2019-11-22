package br.edu.opi.manager.olympiad.services;

import br.edu.opi.manager.olympiad.dtos.ObiGraphRowDTO;
import br.edu.opi.manager.olympiad.dtos.SbcGraphRowDTO;
import br.edu.opi.manager.olympiad.models.ObiAward;
import br.edu.opi.manager.olympiad.models.ObiGraph;
import br.edu.opi.manager.olympiad.models.SbcGraph;
import br.edu.opi.manager.olympiad.repositories.ObiGraphRepository;
import br.edu.opi.manager.olympiad.repositories.SbcGraphRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpiGraphService {

	private static final String OBI_GRAPH = "OBI";

	private ObiGraphRepository obiGraphRepository;
	private SbcGraphRepository sbcGraphRepository;

	@Autowired
	public OpiGraphService(ObiGraphRepository obiGraphRepository, SbcGraphRepository sbcGraphRepository) {
		this.obiGraphRepository = obiGraphRepository;
		this.sbcGraphRepository = sbcGraphRepository;
	}

	public JsonElement generateGraphObi() {
		List<ObiGraph> obiGraphList = obiGraphRepository.findAll();
		StringBuilder dataSb = new StringBuilder();
		for (ObiGraph o : obiGraphList) {
			dataSb.append(new ObiGraphRowDTO(o.getYear(), o.getTotalGold(), ObiAward.GOLD.getName(), o.getObiCategory().getName()));
			dataSb.append(new ObiGraphRowDTO(o.getYear(), o.getTotalSilver(), ObiAward.SILVER.getName(), o.getObiCategory().getName()));
			dataSb.append(new ObiGraphRowDTO(o.getYear(), o.getTotalBronze(), ObiAward.BRONZE.getName(), o.getObiCategory().getName()));
			dataSb.append(new ObiGraphRowDTO(o.getYear(), o.getTotalHonorableMention(), ObiAward.HONORABLE_MENTION.getName(), o.getObiCategory().getName()));
		}
		dataSb = removeLastComma(dataSb);
		final String OBI_TITLE = "Prêmios na Olimpíada Brasileira de Informática";
		String schema = generateSchema(OBI_GRAPH, dataSb.toString(), OBI_TITLE);
		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse(schema);
	}

	public JsonElement generateGraphSbc() {
		List<SbcGraph> sbcGraphList = sbcGraphRepository.findAll();
		StringBuilder dataSb = new StringBuilder();
		for (SbcGraph o : sbcGraphList) {
			dataSb.append(new SbcGraphRowDTO(o.getYear(), o.getTotalGold(), "Ouro"));
			dataSb.append(new SbcGraphRowDTO(o.getYear(), o.getTotalSilver(), "Prata"));
			dataSb.append(new SbcGraphRowDTO(o.getYear(), o.getTotalBronze(), "Bronze"));
		}
		dataSb = removeLastComma(dataSb);
		final String OBI_TITLE = "Prêmios na Maratona de Programação da Sociedade Brasileira de Computação - SBC";
		String schema = generateSchema(OBI_GRAPH, dataSb.toString(), OBI_TITLE);
		JsonParser jsonParser = new JsonParser();
		return jsonParser.parse(schema);
	}

	private StringBuilder removeLastComma(StringBuilder dataSb) {
		if (dataSb.length() > 0) {
			int errorLength = ",".length();
			return dataSb.delete(dataSb.length() - errorLength, dataSb.length()); // remove last ","
		} else {
			return dataSb;
		}
	}

	private String generateSchema(final String type, final String data, String title) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append(" \"$schema\": \"https://vega.github.io/schema/vega-lite/v4.json\",");
		sb.append(String.format(" \"title\": \"%s\",", title));
		sb.append(" \"data\": {");
		sb.append("   \"values\":[");
		sb.append(data);
		sb.append("   ]");
		sb.append(" },");
		sb.append(" \"mark\": {");
		sb.append("   \"type\": \"line\",");
		sb.append("   \"point\": {");
		sb.append("     \"filled\": false,");
		sb.append("     \"fill\": \"white\"");
		sb.append("   }");
		sb.append(" },");
		sb.append(" \"encoding\": {");
		sb.append("   \"x\": {");
		sb.append("     \"field\": \"year\",");
		sb.append("     \"type\": \"ordinal\",");
		sb.append("     \"scale\": {\"padding\": 5},");
		sb.append("     \"axis\": {");
		sb.append("       \"title\": \"Ano\",");
		sb.append("       \"offset\": 10,");
		sb.append("       \"titlePadding\": 10");
		sb.append("     }");
		sb.append("   },");
		sb.append("   \"y\": {");
		sb.append("     \"field\": \"total\",");
		sb.append("     \"type\": \"quantitative\",");
		sb.append("     \"axis\": {");
		sb.append("       \"title\": \"Quantidade\",");
		sb.append("       \"offset\": 10");
		if (!OBI_GRAPH.equals(type)) {
			sb.append(",       \"labelSeparation\": 1000");
		}
		sb.append("     }");
		sb.append("   },");
		sb.append("   \"color\": {");
		sb.append("     \"title\": \"Prêmio\",");
		sb.append("     \"field\": \"award\",");
		sb.append("     \"type\": \"nominal\",");
		sb.append("     \"scale\": {");
		sb.append("       \"range\": [");
		if (!OBI_GRAPH.equals(type)) {
			sb.append("         \"#FEE101\", \"#A7A7AD\", \"#A77044\"");
		} else {
			sb.append("         \"#FEE101\", \"#A7A7AD\", \"#A77044\",\"#1A936F\"");
		}
		sb.append("       ]");
		sb.append("     },");
		sb.append("     \"sort\": {\"field\": \"*\",\"order\": \"ascending\",\"op\": \"count\"}");
		sb.append("   }");
		sb.append(" }");
		sb.append("}");
		return sb.toString();
	}

}
