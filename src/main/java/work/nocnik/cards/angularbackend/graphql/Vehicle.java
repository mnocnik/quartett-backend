package work.nocnik.cards.angularbackend.graphql;

import lombok.Data;

import java.time.Instant;

@Data
public class Vehicle {
  private Instant created = Instant.now();
  private Long version;

  private VehicleType vehicleType;

  private String name;
  private String imageUrl;
  private String description;
}
