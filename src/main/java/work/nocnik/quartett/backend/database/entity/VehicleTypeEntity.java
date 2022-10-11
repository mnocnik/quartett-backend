package work.nocnik.quartett.backend.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
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
import java.util.UUID;

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
  private UUID uuid = UUID.randomUUID();

  @Column(name = "name", nullable = false, unique = true)
  private String name;
  @Column(name = "description", nullable = false, unique = true)
  private String description;
  @Column(name = "image")
  private String image;

  @ToString.Exclude
  @OneToMany(mappedBy = "vehicleType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<VehicleEntity> vehicles = new HashSet<>();

  @ToString.Exclude
  @OneToMany(mappedBy = "vehicleType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<VehiclePropertyEntity> properties = new HashSet<>();
}
