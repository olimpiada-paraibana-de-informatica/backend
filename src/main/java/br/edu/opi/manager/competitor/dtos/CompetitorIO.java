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
import java.util.List;

@Component("competitorIO")
public class CompetitorIO {

	private ModelMapper modelMapper;

	final Converter<CompetitorInput, Competitor> competitorInputConverter = new Converter<CompetitorInput, Competitor>() {
		@Override
		public Competitor convert(MappingContext<CompetitorInput, Competitor> context) {
			CompetitorInput input = context.getSource();
			Competitor competitor =  new Competitor();
			competitor.setStudent(new Student(input.getStudentId()));
			competitor.setGrade(input.getStudentGrade());

			return competitor;
		}
	};

	final Converter<Competitor, CompetitorOutput> competitorOutputConverter = new Converter<Competitor, CompetitorOutput>() {
		@Override
		public CompetitorOutput convert(MappingContext<Competitor, CompetitorOutput> context) {
			Competitor input = context.getSource();
			CompetitorOutput competitorOutput = new CompetitorOutput();
			competitorOutput.setStudentId(input.getStudent().getId());
			competitorOutput.setStudentGrade(input.getGrade());
			competitorOutput.setStudentCategory(input.getCategory());

			return competitorOutput;
		}
	};

	public CompetitorIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(competitorInputConverter);
		this.modelMapper.addConverter(competitorOutputConverter);
	}

	public Competitor mapTo(CompetitorInput competitorInput) {
		return this.modelMapper.map(competitorInput, Competitor.class);
	}

	public CompetitorOutput mapTo(Competitor competitor) {
		return this.modelMapper.map(competitor, CompetitorOutput.class);
	}

//	public List<CompetitorOutput> toList(List<Competitor> source) {
//		Type dest = new TypeToken<List<CompetitorOutput>>() {}.getType();
//		return modelMapper.map(source, dest);
//	}

	public List<Competitor> toList(List<CompetitorInput> source) {
		Type dest = new TypeToken<List<Competitor>>() {}.getType();
		return modelMapper.map(source, dest);
	}

//	public Page<CompetitorOutput> toPage(Page<Competitor> source) {
//		List<CompetitorOutput> list = toList(source.getContent());
//		return new PageImpl<>(list, source.getPageable(), source.getTotalElements());
//	}

}
