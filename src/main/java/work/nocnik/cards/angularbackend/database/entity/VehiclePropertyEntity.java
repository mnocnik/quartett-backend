package work.nocnik.cards.angularbackend.database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

  @Column(name = "property_name", nullable = false)
  private String propertyName;
  @Column(name = "unit_short", nullable = false)
  private String unitShort;
  @Column(name = "sort_index")
  private Integer sortIndex = 0;

  @OneToMany(mappedBy = "vehicleType")
  private Set<VehicleTypePropertyEntity> vehicleTypes = new HashSet<>();
}
