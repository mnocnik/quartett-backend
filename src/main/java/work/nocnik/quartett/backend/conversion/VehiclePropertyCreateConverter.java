package work.nocnik.quartett.backend.conversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehiclePropertyEntity;
import work.nocnik.quartett.backend.graphql.request.VehiclePropertyCreate;

@Service
public class VehiclePropertyCreateConverter implements Converter<VehiclePropertyCreate, VehiclePropertyEntity> {

  @Override
  public VehiclePropertyEntity convert(final VehiclePropertyCreate source) {
    final VehiclePropertyEntity result = new VehiclePropertyEntity();
    result.setName(source.getName());
    result.setUnitShort(source.getUnitShort());
    result.setSortIndex(source.getSortIndex());
    return result;
  }
}
