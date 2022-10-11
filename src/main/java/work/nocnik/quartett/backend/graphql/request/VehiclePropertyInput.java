package work.nocnik.quartett.backend.graphql.request;

import lombok.Data;

import java.util.UUID;

@Data
public class VehiclePropertyInput {
  private Long version;
  private UUID uuid;
  private String name;
  private String unitShort;
  private Integer sortIndex;
}
