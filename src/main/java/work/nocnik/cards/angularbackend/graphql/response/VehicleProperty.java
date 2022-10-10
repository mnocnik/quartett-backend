package work.nocnik.cards.angularbackend.graphql.response;

import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class VehicleProperty {
  private Instant created = Instant.now();
  private Long version;
  private UUID uuid;
  private String name;
  private String unitShort;
  private Integer sortIndex = 0;
  private VehicleType vehicleType;
  private Set<VehicleData> data = new HashSet<>();
}
