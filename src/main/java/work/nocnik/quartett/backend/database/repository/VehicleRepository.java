package work.nocnik.quartett.backend.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import work.nocnik.quartett.backend.database.entity.VehicleEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

  Optional<VehicleEntity> findByUuid(final UUID uuid);

  List<VehicleEntity> findByVehicleTypeUuid(final UUID uuid);
}
