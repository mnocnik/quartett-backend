package work.nocnik.cards.angularbackend.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import work.nocnik.cards.angularbackend.database.entity.VehicleEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

  Optional<VehicleEntity> findByUuid(final UUID uuid);
}
