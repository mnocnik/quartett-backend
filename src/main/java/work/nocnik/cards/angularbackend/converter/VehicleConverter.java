package work.nocnik.cards.angularbackend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.graphql.response.Vehicle;

@Service
public class VehicleConverter implements Converter<VehicleEntity, Vehicle> {
  @Override
  public Vehicle convert(final VehicleEntity source) {
    final Vehicle result = new Vehicle();
    result.setCreated(source.getCreated());
    result.setVersion(source.getVersion());
    result.setUuid(source.getUuid());
    result.setName(source.getName());
    result.setImage(source.getImage());
    result.setDescription(source.getDescription());

//    vehicleType: VehicleTypeEntity
//    data: [VehicleDataEntity]

    return result;
  }
}
