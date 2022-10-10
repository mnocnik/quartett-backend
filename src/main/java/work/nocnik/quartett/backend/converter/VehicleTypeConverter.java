package work.nocnik.quartett.backend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.graphql.response.VehicleType;

@Service
public class VehicleTypeConverter implements Converter<VehicleTypeEntity, VehicleType> {
  @Override
  public VehicleType convert(final VehicleTypeEntity source) {
    final VehicleType result = new VehicleType();
    result.setCreated(source.getCreated());
    result.setVersion(source.getVersion());
    result.setUuid(source.getUuid());
    result.setName(source.getName());
    result.setDescription(source.getDescription());
    result.setImage(source.getImage());

//    vehicles: [VehicleEntity]
//    properties: [VehiclePropertyEntity]

    return result;
  }
}
