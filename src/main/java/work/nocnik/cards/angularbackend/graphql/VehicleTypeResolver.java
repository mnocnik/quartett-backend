package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.database.entity.VehicleTypeEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleTypeRepository;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleTypeResolver {
  private final VehicleTypeRepository vehicleTypeRepository;

  @QueryMapping
  public Flux<VehicleTypeEntity> vehicleTypes(@Argument final UUID typeUUID) {
    if (typeUUID == null) {
      return Flux.fromIterable(this.vehicleTypeRepository.findAll());
    } else {
      return Mono.justOrEmpty(this.vehicleTypeRepository.findByUuid(typeUUID)).flux();
    }
  }

  @SchemaMapping(typeName = "VehicleTypeEntity", field = "vehicles")
  public Flux<VehicleEntity> vehicles(final VehicleTypeEntity entity) { // ChildMapping: do NOT use '@Argument'
    if (entity == null) {
      return Flux.empty();
    }
    return Flux.fromIterable(entity.getVehicles());
  }
}
