package br.edu.opi.manager.school.dto;

import br.edu.opi.manager.delegate.model.Delegate;
import br.edu.opi.manager.school.model.School;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Mapper to User.
 */
@Component("schoolIO")
public class SchoolIO {

	private ModelMapper modelMapper;

	final Converter<SchoolInput, School> userConverter = new Converter<SchoolInput, School>() {
		@Override
		public School convert(MappingContext<SchoolInput, School> context) {
			SchoolInput input = context.getSource();
			return new School(
					input.getSchoolName(),
					input.getSchoolCity(),
					new Delegate(null, input.getDelegateEmail(), input.getDelegateName()));
		}
	};

	public SchoolIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(userConverter);
	}

	public School mapTo(SchoolInput schoolInput) {
		return this.modelMapper.map(schoolInput, School.class);
	}

	public List<SchoolOutput> toList(List<School> source) {
		Type dest = new TypeToken<List<SchoolOutput>>() {
		}.getType();
		return modelMapper.map(source, dest);
	}

	public Set<SchoolOutput> toSet(Set<School> source) {
		Type dest = new TypeToken<Set<SchoolOutput>>() {
		}.getType();
		return modelMapper.map(source, dest);
	}

	public Page<SchoolOutput> toPage(Page<School> source) {
		List<SchoolOutput> list = toList(source.getContent());
		return new PageImpl<>(list, source.getPageable(), source.getTotalElements());
	}

}
