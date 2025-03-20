package edu.ub.pis2425.cleanarchitectureexample.presentation.pos.mappers;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.List;
import java.util.stream.Collectors;

import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ClientId;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.Price;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ProductId;

/*
 * This class is a singleton that provides a generic ModelMapper instance.
 */
public class DomainToPOMapper extends ModelMapper {

  private static final ModelMapper modelMapper = new ModelMapper();

  static {
    modelMapper.getConfiguration()
        .setFieldMatchingEnabled(true) // No need to define setters
        .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
        .setMatchingStrategy(MatchingStrategies.LOOSE);

    modelMapper.addConverter(new AbstractConverter<ProductId, String>() {
      @Override
      protected String convert(ProductId source) {
        return source != null ? source.toString() : null;
      }
    });

    modelMapper.addConverter(new AbstractConverter<ClientId, String>() {
      @Override
      protected String convert(ClientId source) {
        return source != null ? source.toString() : null;
      }
    });

    modelMapper.addConverter(new AbstractConverter<Price, String>() {
      @Override
      protected String convert(Price source) {
        return source != null ? source.toString() : null;
      }
    });
  }

  public static <S, T> T mapObject(S source, Class<T> destinationType) {
    return modelMapper.map(source, destinationType);
  }

  public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
    return source
        .stream()
        .map(element -> modelMapper.map(element, targetClass))
        .collect(Collectors.toList());
  }
}

