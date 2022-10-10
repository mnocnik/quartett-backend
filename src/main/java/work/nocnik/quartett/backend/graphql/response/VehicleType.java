package work.nocnik.quartett.backend.graphql.response;

import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class VehicleType {
  private Instant created = Instant.now();
  private Long version;
  private UUID uuid;
  private String name;
  private String description;
  private String image;
  private Set<Vehicle> vehicles = new HashSet<>();
  private Set<VehicleProperty> properties = new HashSet<>();
}
