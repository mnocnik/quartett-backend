package work.nocnik.cards.angularbackend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.cards.angularbackend.database.entity.VehicleTypeEntity;
import work.nocnik.cards.angularbackend.graphql.VehicleType;

@Service
public class VehicleTypeConverter implements Converter<VehicleTypeEntity, VehicleType> {
  @Override
  public VehicleType convert(final VehicleTypeEntity source) {
    final VehicleType result = new VehicleType();
    result.setCreated(source.getCreated());
    result.setTypeName(source.getTypeName());
    result.setTypeDescription(source.getTypeDescription());
    result.setTypeImage(source.getTypeImage());
    return result;
  }
}
