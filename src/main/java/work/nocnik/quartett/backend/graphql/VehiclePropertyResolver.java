package work.nocnik.quartett.backend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import work.nocnik.quartett.backend.database.entity.VehicleDataEntity;
import work.nocnik.quartett.backend.database.entity.VehiclePropertyEntity;
import work.nocnik.quartett.backend.database.entity.VehicleTypeEntity;

@Controller
@RequiredArgsConstructor
public class VehiclePropertyResolver {

  @SchemaMapping
  public Flux<VehiclePropertyEntity> properties(final VehicleTypeEntity entity) { // ChildMapping: do NOT use '@Argument'
    return Flux.fromIterable(entity.getProperties());
  }

  @SchemaMapping
  public Mono<VehiclePropertyEntity> property(final VehicleDataEntity entity) { // ChildMapping: do NOT use '@Argument'
    return Mono.justOrEmpty(entity.getProperty());
  }
}
