package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import work.nocnik.cards.angularbackend.database.entity.VehicleTypeEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleTypeRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VehicleTypeQueryResolver {
  private final VehicleTypeRepository vehicleTypeRepository;

  @QueryMapping
  public List<VehicleTypeEntity> vehicleType(@Argument String name) {
    return this.vehicleTypeRepository.findByName(name);
  }
}
