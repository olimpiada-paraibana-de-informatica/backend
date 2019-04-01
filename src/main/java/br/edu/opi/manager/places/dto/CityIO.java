package br.edu.opi.manager.places.dto;

import br.edu.opi.manager.places.model.City;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component("cityIO")
public class CityIO {

	private ModelMapper modelMapper;

	final Converter<City, CityOutput> cityOutputConverter = new Converter<City, CityOutput>() {
		@Override
		public CityOutput convert(MappingContext<City, CityOutput> context) {
			// @formatter:off
			City input = context.getSource();
			CityOutput output = new CityOutput(
					input.getCboCode(),
					input.getName(),
					input.getUf() != null ?
							input.getUf().getAbbreviation() : null);
			return output;
			// @formatter:on
		}
	};

	public CityIO() {
		this.modelMapper = new ModelMapper();
		this.modelMapper.addConverter(cityOutputConverter);
	}

	public CityOutput mapTo(City city) {
		return this.modelMapper.map(city, CityOutput.class);
	}

	public List<CityOutput> toList(List<City> cities) {
		Type type = new TypeToken<List<CityOutput>>(){}.getType();
		return this.modelMapper.map(cities, type);
	}

}
