package work.nocnik.cards.angularbackend.graphql;

import lombok.Data;

import java.time.Instant;

@Data
public class VehicleType {
  private Instant created = Instant.now();
  private Long version;

  private String typeName;
  private String typeDescription;
  private String typeImage;
//  private Set<VehicleEntity> vehicles = new HashSet<>();
//  private Set<VehicleTypePropertyEntity> vehicleProperties = new HashSet<>();
}
