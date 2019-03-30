package br.edu.opi.manager.conventions.dto;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Generic mapper to DTOs.
 */
@Component("appControllerBase")
public class AppControllerBase {

	private ModelMapper modelMapper;

	public AppControllerBase() {
		this.modelMapper = new ModelMapper();
	}

	public <S, D> D mapTo(S source, Class<D> dest) {
		return modelMapper.map(source, dest);
	}

	public <S, D> List<D> toList(List<S> source, Type dest) {
		return modelMapper.map(source, dest);
	}

	public <S, D> Set<D> toSet(Set<S> source, Type dest) {
		return modelMapper.map(source, dest);
	}

	public <S, D> Page<D> toPage(Page<S> source, Type dest) {
		List<D> list = toList(source.getContent(), dest);
		return new PageImpl<>(list, source.getPageable(), source.getTotalElements());
	}

}
