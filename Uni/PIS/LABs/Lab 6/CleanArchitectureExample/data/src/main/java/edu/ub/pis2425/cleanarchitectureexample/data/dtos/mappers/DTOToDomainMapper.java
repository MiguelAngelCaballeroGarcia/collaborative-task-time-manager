package edu.ub.pis2425.cleanarchitectureexample.data.dtos.mappers;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ClientId;
import edu.ub.pis2425.cleanarchitectureexample.domain.valueobjects.ProductId;

/*
 * This class is a singleton that provides a generic ModelMapper instance.
 */
public class DTOToDomainMapper extends ModelMapper {

  private static final ModelMapper modelMapper = new ModelMapper();

  static {
    modelMapper.getConfiguration()
      .setFieldMatchingEnabled(true) // No need to define setters
      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
      .setMatchingStrategy(MatchingStrategies.LOOSE);

    /* Conversió entre tipus no coincidents */
    modelMapper.addConverter(new AbstractConverter<String, ClientId>() {
      @Override
      protected ClientId convert(String source) {
        return new ClientId(source);
      }
    });
    modelMapper.addConverter(new AbstractConverter<String, ProductId>() {
      @Override
      protected ProductId convert(String source) {
        return new ProductId(source);
      }
    });
    modelMapper.addConverter(new AbstractConverter<ClientId, String>() {
      @Override
      protected String convert(ClientId clientId) {
        return clientId.toString();
      }
    });
    modelMapper.addConverter(new AbstractConverter<ProductId, String>() {
      @Override
      protected String convert(ProductId productId) {
        return productId.getId();
      }
    });
  }

  /*
   * Mapeja un objecte a un altre objecte de la classe especificada.
   * No caldria definir-lo perquè la classe ModelMapper ja té un mètode
   * amb la mateixa signatura, però així podem fer que la classe implementi
   * l'interfície DataMapper i veiem que si no re-aprofitéssim ModelMapper
   * hauríem de definir-lo.
   */

  public static <S, T> T mapObject(S source, Class<T> destinationType) {
    return modelMapper.map(source, destinationType);
  }
}
