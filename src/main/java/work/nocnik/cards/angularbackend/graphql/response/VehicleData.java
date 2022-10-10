package work.nocnik.cards.angularbackend.graphql.response;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class VehicleData {
  private Instant created = Instant.now();
  private Long version;
  private UUID uuid;
  private String value;
  private VehicleProperty property;
  private Vehicle vehicle;
}
