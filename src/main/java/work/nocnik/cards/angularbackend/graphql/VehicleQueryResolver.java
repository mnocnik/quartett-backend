package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class VehicleQueryResolver {
  private final VehicleRepository vehicleRepository;

  @RolesAllowed("DEV")
  @QueryMapping
  public List<VehicleEntity> vehicles() {
    return this.vehicleRepository.findAll();
  }

  @QueryMapping
  public List<VehicleEntity> vehiclesOfType(@Argument final String typeName) {
    return this.vehicleRepository.findAll().stream()
        .filter(entity -> this.sameTypeName(entity, typeName))
        .toList();
  }

  private boolean sameTypeName(final VehicleEntity entity, final String typeName) {
    return typeName.equals(entity.getVehicleType().getTypeName());
  }
}
