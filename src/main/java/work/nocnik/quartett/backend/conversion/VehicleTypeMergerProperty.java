package work.nocnik.quartett.backend.conversion;

import lombok.Getter;
import org.springframework.stereotype.Service;
import work.nocnik.quartett.backend.database.entity.VehiclePropertyEntity;
import work.nocnik.quartett.backend.database.merger.EntityMergerCollection;
import work.nocnik.quartett.backend.graphql.request.VehiclePropertyInput;

import java.util.function.BiFunction;
import java.util.function.Supplier;

@Service
public class VehicleTypeMergerProperty implements EntityMergerCollection<VehiclePropertyInput, VehiclePropertyEntity> {
  @Getter // EntityMergerCollection
  private final Supplier<VehiclePropertyEntity> newSupplier = VehiclePropertyEntity::new;
  @Getter // EntityMergerCollection
  private final BiFunction<VehiclePropertyInput, VehiclePropertyEntity, Boolean> comparator = (dto, entity) -> this.compare(dto::getUuid, entity::getUuid);

  @Override
  public VehiclePropertyEntity mergeEntity(final VehiclePropertyInput vehiclePropertyInput, final VehiclePropertyEntity vehiclePropertyEntity) {
    vehiclePropertyEntity.setVersion(vehiclePropertyInput.getVersion());
    vehiclePropertyEntity.setName(vehiclePropertyInput.getName());
    vehiclePropertyEntity.setUnitShort(vehiclePropertyInput.getUnitShort());
    vehiclePropertyEntity.setSortIndex(vehiclePropertyInput.getSortIndex());
    return vehiclePropertyEntity;
  }
}
