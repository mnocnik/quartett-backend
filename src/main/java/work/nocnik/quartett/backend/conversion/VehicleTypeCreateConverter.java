package work.nocnik.quartett.backend.conversion;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeCreate;

@Service
@RequiredArgsConstructor
public class VehicleTypeCreateConverter implements Converter<VehicleTypeCreate, VehicleTypeEntity> {

  @Override
  public VehicleTypeEntity convert(final VehicleTypeCreate source) {
    final VehicleTypeEntity result = new VehicleTypeEntity();
    result.setName(source.getName());
    result.setDescription(source.getDescription());
    result.setImage(source.getImage());
    return result;
  }
}
