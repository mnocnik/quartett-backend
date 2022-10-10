package work.nocnik.quartett.backend.graphql.response;

import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class Vehicle {
  private Instant created = Instant.now();
  private Long version;
  private UUID uuid;
  private String name;
  private String image;
  private String description;
  private VehicleType vehicleType;
  private Set<VehicleData> data = new HashSet<>();
}
