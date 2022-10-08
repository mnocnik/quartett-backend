package work.nocnik.cards.angularbackend.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "vehicle_data")
@Getter
@Setter
@ToString
public class VehicleDataEntity {
  @EmbeddedId
  private VehicleDataId id = new VehicleDataId();

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("vehicleId")
  private VehicleEntity vehicle;
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("vehiclePropertyId")
  private VehiclePropertyEntity vehicleProperty;

  @Column(name = "value")
  private String value;

  @Embeddable
  public class VehicleDataId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long vehicleId;
    private Long vehiclePropertyId;
  }
}
