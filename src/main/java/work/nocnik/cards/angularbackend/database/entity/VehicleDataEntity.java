package work.nocnik.cards.angularbackend.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "vehicle_data")
@Getter
@Setter
@ToString
public class VehicleDataEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant created = Instant.now();
  @Version
  private Long version;
  @Column(name = "uuid", unique = true, updatable = false)
  private UUID uuid;

  private String value;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "vehicle_property_id", nullable = false)
  private VehiclePropertyEntity property;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "vehicle_id", nullable = false)
  private VehicleEntity vehicle;
}
