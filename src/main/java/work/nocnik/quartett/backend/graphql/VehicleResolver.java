package work.nocnik.quartett.backend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.nocnik.quartett.backend.database.entity.VehicleEntity;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.database.repository.VehicleRepository;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleResolver {
  private final VehicleRepository vehicleRepository;

  @QueryMapping
  public Mono<VehicleEntity> vehicle(@Argument final UUID uuid) {
    return Mono.justOrEmpty(this.vehicleRepository.findByUuid(uuid));
  }

  @QueryMapping
  public Flux<VehicleEntity> vehiclesByType(@Argument final UUID typeUUID) {
    if (typeUUID == null) {
      return Flux.fromIterable(this.vehicleRepository.findAll());
    } else {
      return Flux.fromIterable(this.vehicleRepository.findByVehicleTypeUuid(typeUUID));
    }
  }

  @SchemaMapping
  public Mono<VehicleTypeEntity> vehicleType(final VehicleEntity entity) { // ChildMapping: do NOT use '@Argument'
    return Mono.justOrEmpty(entity.getVehicleType());
  }
}
