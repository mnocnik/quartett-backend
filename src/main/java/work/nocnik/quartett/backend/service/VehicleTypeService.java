package work.nocnik.quartett.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.database.repository.VehicleTypeRepository;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeCreate;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeUpdate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleTypeService {
  private final ConversionService conversionService;
  private final VehicleTypeRepository vehicleTypeRepository;

  public VehicleTypeEntity createVehicleType(final VehicleTypeCreate input) {
    final VehicleTypeEntity entity = this.conversionService.convert(input, VehicleTypeEntity.class);
    if (entity == null) {
      throw new RuntimeException("could not createVehicleType");
    }
    log.debug("created VehicleType");
    return this.vehicleTypeRepository.save(entity);
  }

  public VehicleTypeEntity updateVehicleType(final VehicleTypeUpdate input) {
    final Optional<VehicleTypeEntity> optEntity = this.vehicleTypeRepository.findByUuid(input.getUuid());
    if (optEntity.isEmpty()) {
      throw new RuntimeException("VehicleType with uuid " + input.getUuid() + " not found");
    }
    final VehicleTypeEntity result = optEntity.get();
    result.setVersion(input.getVersion());
    result.setName(input.getName());
    result.setDescription(input.getDescription());
    result.setImage(input.getImage());
    return this.vehicleTypeRepository.save(result);
  }

  public void removeVehicleType(final UUID typeUUID) {
    this.vehicleTypeRepository.deleteByUuid(typeUUID);
  }
}
