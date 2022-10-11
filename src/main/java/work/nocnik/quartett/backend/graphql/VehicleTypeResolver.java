package work.nocnik.quartett.backend.graphql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.nocnik.quartett.backend.database.entity.VehicleEntity;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;
import work.nocnik.quartett.backend.database.repository.VehicleTypeRepository;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeCreate;
import work.nocnik.quartett.backend.graphql.request.VehicleTypeUpdate;
import work.nocnik.quartett.backend.service.VehicleTypeService;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class VehicleTypeResolver {
  private final VehicleTypeService vehicleTypeService;
  private final VehicleTypeRepository vehicleTypeRepository;

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
  public Mono<VehicleTypeEntity> createVehicleType(@Argument final VehicleTypeCreate input) {
    return Mono.justOrEmpty(this.vehicleTypeService.createVehicleType(input));
  }

  @MutationMapping
  public Mono<VehicleTypeEntity> updateVehicleType(@Argument final VehicleTypeUpdate input) {
    return Mono.justOrEmpty(this.vehicleTypeService.updateVehicleType(input));
  }

  @MutationMapping
  public Mono<UUID> removeVehicleType(@Argument final UUID typeUUID) {
    this.vehicleTypeService.removeVehicleType(typeUUID);
    return Mono.just(typeUUID);
  }
}
