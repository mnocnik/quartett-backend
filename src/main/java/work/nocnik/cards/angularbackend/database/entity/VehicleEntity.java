package work.nocnik.cards.angularbackend.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@ToString
public class VehicleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant created = Instant.now();
  @Version
  private Long version;
  @Column(name = "uuid", unique = true, updatable = false)
  private UUID uuid;

  private String name;
  private String image;
  private String description;

  @ManyToOne(optional = false)
  @JoinColumn(name = "vehicle_type_id", nullable = false)
  private VehicleTypeEntity vehicleType;

  @ToString.Exclude
  @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
  private Set<VehicleDataEntity> data = new HashSet<>();

}
