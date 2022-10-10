package work.nocnik.cards.angularbackend.graphql;

import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import work.nocnik.cards.angularbackend.database.entity.VehicleDataEntity;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;

@Controller
public class VehicleDataResolver {

  @SchemaMapping
  public Flux<VehicleDataEntity> data(final VehicleEntity entity) {
    return Flux.fromIterable(entity.getData());
  }
}
