package work.nocnik.quartett.backend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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
import work.nocnik.quartett.backend.graphql.request.VehicleTypeInput;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleTypeResolver {
  private final ConversionService conversionService;
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
  public Mono<VehicleTypeEntity> modifyVehicleType(@Argument final VehicleTypeInput input) {
    if (input.getUuid() == null) { // assume new 'Type'
      final VehicleTypeEntity newEntity = this.conversionService.convert(input, VehicleTypeEntity.class);
      if (newEntity == null) {
        throw new RuntimeException("could not create VehicleType");
      }
      return Mono.justOrEmpty(this.vehicleTypeRepository.save(newEntity));
    } else {
      final Optional<VehicleTypeEntity> optEntity = this.vehicleTypeRepository.findByUuid(input.getUuid());
      if (optEntity.isEmpty()) {
        throw new RuntimeException("could not find VehicleType");
      }
      final VehicleTypeEntity existingEntity = optEntity.get();
      if (!existingEntity.getVersion().equals(input.getVersion())) {
        throw new RuntimeException("tried to modify a deprecated version of an entity");
      }
      existingEntity.setName(input.getName());
      existingEntity.setDescription(input.getDescription());
      existingEntity.setImage(input.getImage());

      // TODO: merge 'input.properties' into 'Entity.properties'


      this.vehicleTypeRepository.save(existingEntity);
    }
    return null;
  }
}
