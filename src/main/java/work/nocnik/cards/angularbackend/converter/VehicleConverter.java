package work.nocnik.cards.angularbackend.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.graphql.Vehicle;
import work.nocnik.cards.angularbackend.graphql.VehicleType;

@Service
@RequiredArgsConstructor
public class VehicleConverter implements Converter<VehicleEntity, Vehicle> {
  private final ConversionService conversionService;

  @Override
  public Vehicle convert(final VehicleEntity source) {
    final Vehicle result = new Vehicle();
    result.setCreated(source.getCreated());
    result.setName(source.getName());
    result.setDescription(source.getDescription());
    result.setImageUrl(source.getImageUrl());
    result.setVehicleType(this.conversionService.convert(source.getVehicleType(), VehicleType.class));
    return result;
  }
}
