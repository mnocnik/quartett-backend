package work.nocnik.cards.angularbackend.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;
import work.nocnik.cards.angularbackend.database.repository.VehicleRepository;

import java.util.List;
import java.util.Objects;

@Controller
@Component
@RequiredArgsConstructor
public class VehicleQueryResolver {
  private final ConversionService conversionService;
  private final VehicleRepository vehicleRepository;

  @QueryMapping
  public List<Vehicle> vehiclesOfType(@Argument final String typeName) {
    return this.vehicleRepository.findAll().stream()
        .filter(entity -> this.sameTypeName(entity, typeName))
        .map(entity -> this.conversionService.convert(entity, Vehicle.class))
        .filter(Objects::nonNull)
        .toList();
  }

  @SchemaMapping(typeName = "Vehicle", field = "vehicleType")
  public VehicleType vehicleType(final Vehicle vehicle) {
    return this.conversionService.convert(vehicle.getVehicleType(), VehicleType.class);
  }

  private boolean sameTypeName(final VehicleEntity entity, final String typeName) {
    return typeName.equals(entity.getVehicleType().getTypeName());
  }
}
