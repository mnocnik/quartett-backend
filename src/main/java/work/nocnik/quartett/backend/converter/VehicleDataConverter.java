package work.nocnik.quartett.backend.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehicleDataEntity;
import work.nocnik.quartett.backend.graphql.response.VehicleData;

@Service
public class VehicleDataConverter implements Converter<VehicleDataEntity, VehicleData> {
  @Override
  public VehicleData convert(final VehicleDataEntity source) {
    final VehicleData result = new VehicleData();
    result.setCreated(source.getCreated());
    result.setVersion(source.getVersion());
    result.setUuid(source.getUuid());
    result.setValue(source.getValue());

//    property: VehiclePropertyEntity
//    vehicle: VehicleEntity

    return result;
  }
}
