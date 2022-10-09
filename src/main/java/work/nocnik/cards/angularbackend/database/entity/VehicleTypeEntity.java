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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicle_type")
@Getter
@Setter
@ToString
public class VehicleTypeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant created = Instant.now();
  @Version
  private Long version;
  @Column(name = "uuid", unique = true, updatable = false)
  private String uuid;

  @Column(name = "name", nullable = false, unique = true)
  private String name;
  @Column(name = "description", nullable = false, unique = true)
  private String description;
  @Column(name = "image")
  private String image;

  @OneToMany(mappedBy = "vehicleType", fetch = FetchType.LAZY)
  private Set<VehicleEntity> vehicles = new HashSet<>();

  @OneToMany(mappedBy = "vehicleType", fetch = FetchType.LAZY)
  private Set<VehiclePropertyEntity> properties = new HashSet<>();
}
