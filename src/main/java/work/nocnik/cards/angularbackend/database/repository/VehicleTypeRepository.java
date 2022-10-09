package work.nocnik.cards.angularbackend.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import work.nocnik.cards.angularbackend.database.entity.VehicleTypeEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface VehicleTypeRepository extends JpaRepository<VehicleTypeEntity, Long> {

  Optional<VehicleTypeEntity> findByUuid(final UUID uuid);

  List<VehicleTypeEntity> findByName(final String name);
}
