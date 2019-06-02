package br.edu.opi.manager.competitor.dtos;

import br.edu.opi.manager.competitor.models.Competitor;
import br.edu.opi.manager.student.models.Student;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

@Component("competitorIO")
public class CompetitorIO {

	private ModelMapper modelMapper;

	final Converter<CompetitorInput, Competitor> competitorInputConverter = new Converter<CompetitorInput, Competitor>() {
		@Override
		public Competitor convert(MappingContext<CompetitorInput, Competitor> context) {
			CompetitorInput input = context.getSource();
			Competitor competitor = new Competitor();
			competitor.setStudent(new Student(input.getStudentId()));
			competitor.setGrade(input.getStudentGrade());
			competitor.setScoreLevelOne(input.getScoreLevelOne());
			competitor.setScoreLevelTwo(input.getScoreLevelTwo());
			return competitor;
		}
	};

	final Converter<Competitor, CompetitorOutput> competitorOutputConverter = new Converter<Competitor, CompetitorOutput>() {
		@Override
		public CompetitorOutput convert(MappingContext<Competitor, CompetitorOutput> context) {
			Competitor input = context.getSource();
			return toCompetitorOutput(input);
		}
	};

	final Converter<List<Competitor>, List<CompetitorOutput>> listCompetitorOutputConverter = new Converter<List<Competitor>, List<CompetitorOutput>>() {
		@Override
		public List<CompetitorOutput> convert(MappingContext<List<Competitor>, List<CompetitorOutput>> context) {
			List<Competitor> input = context.getSource();
			List<CompetitorOutput> output = new LinkedList<>();
			for (Competitor competitor : input) {
				output.add(toCompetitorOutput(competitor));
			}
			return output;
		}
	};

	public CompetitorIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(competitorInputConverter);
		this.modelMapper.addConverter(competitorOutputConverter);
		this.modelMapper.addConverter(listCompetitorOutputConverter);
	}

	public Competitor mapTo(CompetitorInput competitorInput) {
		return this.modelMapper.map(competitorInput, Competitor.class);
	}

	public CompetitorOutput mapTo(Competitor competitor) {
		return this.modelMapper.map(competitor, CompetitorOutput.class);
	}

	public List<CompetitorOutput> toList(List<Competitor> source) {
		Type dest = new TypeToken<List<CompetitorOutput>>() {
		}.getType();
		return modelMapper.map(source, dest);
	}

	public Page<CompetitorOutput> toPage(Page<Competitor> source) {
		List<CompetitorOutput> list = toList(source.getContent());
		return new PageImpl<>(list, source.getPageable(), source.getTotalElements());
	}

	private CompetitorOutput toCompetitorOutput(Competitor competitor) {
		CompetitorOutput competitorOutput = new CompetitorOutput();
		competitorOutput.setId(competitor.getId());
		competitorOutput.setScoreLevelOne(competitor.getScoreLevelOne());
		competitorOutput.setScoreLevelTwo(competitor.getScoreLevelTwo());
		competitorOutput.setFinalScore(competitor.getFinalScore());
		competitorOutput.setGrade(competitor.getGrade().getName());
		competitorOutput.setCategory(competitor.getCategory().getName());
		competitorOutput.setLevel(competitor.getLevel().getFriendlyName());
		if (competitor.getStudent() != null && competitor.getStudent().getPerson() != null) {
			Student student = competitor.getStudent();
			competitorOutput.setStudentId(student.getId());
			competitorOutput.setName(student.getPerson().getFullName());
			competitorOutput.setDateBirth(student.getPerson().getDateBirth());
			competitorOutput.setGenre(student.getPerson().getGenre().getName());
		}
		return competitorOutput;
	}

}
