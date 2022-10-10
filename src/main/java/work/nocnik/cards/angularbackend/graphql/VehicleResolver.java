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
import work.nocnik.cards.angularbackend.database.repository.VehicleRepository;

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
    if (entity == null) {
      return Mono.empty();
    }
    return Mono.just(entity.getVehicleType());
  }
}
