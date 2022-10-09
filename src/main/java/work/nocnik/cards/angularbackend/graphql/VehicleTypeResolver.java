package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.database.entity.VehicleTypeEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleTypeRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleTypeResolver {
  private final VehicleTypeRepository vehicleTypeRepository;

  @QueryMapping
  public List<VehicleTypeEntity> vehicleTypes(@Argument final UUID typeUUID) {
    if (typeUUID == null) {
      return this.vehicleTypeRepository.findAll();
    } else {
      return this.vehicleTypeRepository.findByUuid(typeUUID)
          .map(List::of)
          .orElseGet(List::of);
    }
  }

  @SchemaMapping(typeName = "VehicleTypeEntity", field = "vehicles")
  public Set<VehicleEntity> vehicles(final VehicleTypeEntity entity) { // ChildMapping: do NOT use '@Argument'
    if (entity == null) {
      return Set.of();
    }
    return entity.getVehicles();
  }
}
