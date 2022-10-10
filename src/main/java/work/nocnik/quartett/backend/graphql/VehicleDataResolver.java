package work.nocnik.quartett.backend.graphql;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import work.nocnik.quartett.backend.database.entity.VehicleDataEntity;
import work.nocnik.quartett.backend.database.entity.VehicleEntity;

@Controller
public class VehicleDataResolver {

  @SchemaMapping
  public Flux<VehicleDataEntity> data(final VehicleEntity entity) {
    return Flux.fromIterable(entity.getData());
  }
}
