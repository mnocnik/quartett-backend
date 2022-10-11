package work.nocnik.quartett.backend.graphql.request;

import lombok.Data;

@Data
public class VehiclePropertyCreate {
  private String name;
  private String unitShort;
  private Integer sortIndex;
}
