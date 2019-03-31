package br.edu.opi.manager.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

/**
 * Util class for Mapper Model operations.
 */
@Component("mapperUtil")
public final class MapperUtil {

	/**
	 * Model mapper.
	 */
	protected ModelMapper modelMapper;

	/**
	 * Default Constructor.
	 */
	public MapperUtil() {
		this.modelMapper = new ModelMapper();
	}

	/**
	 * Maps the source to destination class.
	 * 
	 * @param source    Source.
	 * @param destClass Destination class.
	 * @return Instance of destination class.
	 */
	public <S, D> D mapTo(S source, Class<D> destClass) {
		return this.modelMapper.map(source, destClass);
	}

	/**
	 * Converts the source list to a destination list mapping the source items to
	 * destination type items.
	 * 
	 * @param source    Source items.
	 * @param destClass Destination class.
	 * @return List of instances of destination type.
	 */
	public <S, D> List<D> toList(List<S> source, Type destClass) {
		return this.modelMapper.map(source, destClass);
	}
	
	/**
	 * Converts the source set to a destination set mapping the source items to
	 * destination type items.
	 * 
	 * @param source    Source items.
	 * @param destClass Destination class.
	 * @return List of instances of destination type.
	 */
	public <S, D> Set<D> toSet(Set<S> source, Type destClass) {
		return this.modelMapper.map(source, destClass);
	}

}
