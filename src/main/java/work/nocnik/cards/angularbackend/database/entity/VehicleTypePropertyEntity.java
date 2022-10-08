package work.nocnik.cards.angularbackend.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "vehicle_type_property")
@Getter
@Setter
@ToString
public class VehicleTypePropertyEntity {
  @EmbeddedId
  private VehicleTypePropertyId id = new VehicleTypePropertyId();
//  @Version
//  private Long version;

  @ManyToOne
  @MapsId("vehicleTypeId")
  private VehicleTypeEntity vehicleType;
  @ManyToOne
  @MapsId("vehiclePropertyId")
  private VehiclePropertyEntity vehicleProperty;

  @Embeddable
  public static class VehicleTypePropertyId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long vehicleTypeId;
    private Long vehiclePropertyId;
  }
}
