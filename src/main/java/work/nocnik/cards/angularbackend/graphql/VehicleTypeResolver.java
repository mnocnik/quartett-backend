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
  public VehicleTypeEntity vehicleTypeByUUID(@Argument UUID uuid) {
    return this.vehicleTypeRepository.findByUuid(uuid).orElse(null);
  }

  @QueryMapping
  public List<VehicleTypeEntity> vehicleType(@Argument String name) {
    return this.vehicleTypeRepository.findByName(name);
  }

  @SchemaMapping(typeName = "VehicleTypeEntity", field = "vehicles")
  public Set<VehicleEntity> vehicles(VehicleTypeEntity entity) {
    if (entity == null) {
      return Set.of();
    }
    return entity.getVehicles();
  }
}
