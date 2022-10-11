package work.nocnik.quartett.backend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.nocnik.quartett.backend.conversion.VehicleTypeMerger;
import work.nocnik.quartett.backend.database.entity.VehicleEntity;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.database.repository.VehicleTypeRepository;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeInput;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleTypeResolver {
  private final VehicleTypeRepository vehicleTypeRepository;
  private final VehicleTypeMerger vehicleTypeMerger;

  @QueryMapping
  public Flux<VehicleTypeEntity> vehicleTypes(@Argument final UUID typeUUID) {
    if (typeUUID == null) {
      return Flux.fromIterable(this.vehicleTypeRepository.findAll());
    } else {
      return Mono.justOrEmpty(this.vehicleTypeRepository.findByUuid(typeUUID)).flux();
    }
  }

  @SchemaMapping
  public Flux<VehicleEntity> vehicles(final VehicleTypeEntity entity) { // ChildMapping: do NOT use '@Argument'
    return Flux.fromIterable(entity.getVehicles());
  }

  @MutationMapping
  public Mono<VehicleTypeEntity> modifyVehicleType(@Argument final VehicleTypeInput vehicleTypeInput) {
    final VehicleTypeEntity result = this.vehicleTypeMerger.merge(vehicleTypeInput);
    return Mono.justOrEmpty(result);
  }

  @MutationMapping
  public Mono<UUID> removeVehicleType(@Argument final UUID typeUUID) {
    this.vehicleTypeRepository.deleteByUuid(typeUUID);
    return Mono.just(typeUUID);
  }
}
