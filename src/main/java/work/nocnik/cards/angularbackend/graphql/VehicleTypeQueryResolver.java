package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import work.nocnik.cards.angularbackend.database.entity.VehicleTypeEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleTypeRepository;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleTypeQueryResolver {
  private final VehicleTypeRepository vehicleTypeRepository;

  @QueryMapping
  public VehicleTypeEntity vehicleTypeByUUID(@Argument UUID uuid) {
    return this.vehicleTypeRepository.findByUuid(uuid).orElse(null);
  }

  @QueryMapping
  public List<VehicleTypeEntity> vehicleType(@Argument String name) {
    return this.vehicleTypeRepository.findByName(name);
  }

//  @SchemaMapping(typeName = "VehicleEntity")
//  public VehicleEntity vehicles(@Argument VehicleEntity entity) {
//    return entity;
//  }
}
