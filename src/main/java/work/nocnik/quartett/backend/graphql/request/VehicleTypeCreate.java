package work.nocnik.quartett.backend.graphql.request;

import lombok.Data;

@Data
public class VehicleTypeCreate {
  private String name;
  private String description;
  private String image;
}
