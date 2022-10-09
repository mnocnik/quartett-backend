package work.nocnik.cards.angularbackend.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Table(name = "vehicle_property")
@Getter
@Setter
@ToString
public class VehiclePropertyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Instant created = Instant.now();
  @Version
  private Long version;
  @Column(name = "uuid", unique = true, updatable = false)
  private String uuid;

  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "unit_short", nullable = false)
  private String unitShort;
  @Column(name = "sort_index")
  private Integer sortIndex = 0;

  @JsonIgnore
  @ManyToOne(optional = false)
  @JoinColumn(name = "vehicle_type_id", nullable = false)
  private VehicleTypeEntity vehicleType;

  @OneToMany(mappedBy = "property", fetch = FetchType.LAZY)
  private Set<VehicleDataEntity> data = new HashSet<>();

}
