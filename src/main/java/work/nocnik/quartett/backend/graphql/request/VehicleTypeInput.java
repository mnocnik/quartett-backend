package work.nocnik.quartett.backend.graphql.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class VehicleTypeInput {
  private Long version;
  private UUID uuid;
  private String name;
  private String description;
  private String image;
  private List<VehiclePropertyInput> properties = new ArrayList<>();
}
