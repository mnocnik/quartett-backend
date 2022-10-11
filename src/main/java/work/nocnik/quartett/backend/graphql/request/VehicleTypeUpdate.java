package work.nocnik.quartett.backend.graphql.request;

import lombok.Data;

import java.util.UUID;

@Data
public class VehicleTypeUpdate {
  private Long version;
  private UUID uuid;
  private String name;
  private String description;
  private String image;
}
