package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class VehicleResolver {
  private final VehicleRepository vehicleRepository;

  @RolesAllowed("DEV")
  @QueryMapping
  public List<VehicleEntity> vehicles(@Argument final UUID typeUUID) {
    if (typeUUID == null) {
      return this.vehicleRepository.findAll();
    } else {
      return this.vehicleRepository.findAll().stream()
          .filter(entity -> typeUUID.equals(entity.getVehicleType().getUuid()))
          .toList();
    }
  }
}
