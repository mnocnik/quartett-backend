package work.nocnik.quartett.backend.conversion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import work.nocnik.quartett.backend.database.entity.VehiclePropertyEntity;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.database.merger.EntityMerger;
import work.nocnik.quartett.backend.database.repository.VehicleTypeRepository;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeInput;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleTypeMerger implements EntityMerger<VehicleTypeInput, VehicleTypeEntity> {
  private final VehicleTypeRepository vehicleTypeRepository;
  private final VehicleTypeMergerProperty vehicleTypeMergerProperty;

  @Override
  public VehicleTypeEntity mergeEntity(final VehicleTypeInput vehicleTypeInput, final VehicleTypeEntity vehicleTypeEntity) {
    vehicleTypeEntity.setVersion(vehicleTypeInput.getVersion());
    vehicleTypeEntity.setName(vehicleTypeInput.getName());
    vehicleTypeEntity.setDescription(vehicleTypeInput.getDescription());
    vehicleTypeEntity.setImage(vehicleTypeInput.getImage());

    // OneToMany
    final Collection<VehiclePropertyEntity> properties =
        this.vehicleTypeMergerProperty.mergeChildren(vehicleTypeInput.getProperties(), vehicleTypeEntity.getProperties(), VehiclePropertyEntity::getId);
    log.trace("VehicleType with uuid {} and name {} now contains {} Properties", vehicleTypeEntity.getUuid(), vehicleTypeEntity.getName(), properties.size());

    return vehicleTypeEntity;
  }

  @Transactional
  public VehicleTypeEntity merge(@Valid final VehicleTypeInput vehicleTypeInput) {
    final VehicleTypeEntity vehicleTypeEntity = this.vehicleTypeRepository
        .findByUuid(vehicleTypeInput.getUuid())
        .orElseGet(VehicleTypeEntity::new);

    this.mergeEntity(vehicleTypeInput, vehicleTypeEntity);
    vehicleTypeEntity.getProperties().forEach(child -> child.setVehicleType(vehicleTypeEntity));
    log.debug("created VehicleTypeEntity with uuid {} and name {}", vehicleTypeEntity.getUuid(), vehicleTypeEntity.getName());
    return this.vehicleTypeRepository.save(vehicleTypeEntity);
  }
}
