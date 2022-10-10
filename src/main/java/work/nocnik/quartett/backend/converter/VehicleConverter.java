package work.nocnik.quartett.backend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehicleEntity;
import work.nocnik.quartett.backend.graphql.response.Vehicle;

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
