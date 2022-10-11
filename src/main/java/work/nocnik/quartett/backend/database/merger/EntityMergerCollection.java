package work.nocnik.quartett.backend.database.merger;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface EntityMergerCollection<DTO, ENTITY> extends EntityMerger<DTO, ENTITY> {

  /**
   * Used in 'matchMapping' to find provide a new ENTITY, if no corresponding exists. Should be provided with a Getter.
   *
   * @return ENTITY::new
   */
  Supplier<ENTITY> getNewSupplier();

  /**
   * Comparator for 'picking' the corresponding Entity (DTO compared to Entity). Provided with a Getter.
   *
   * @return methodology to compare DTO versus Entity
   */
  BiFunction<DTO, ENTITY, Boolean> getComparator();

  default Collection<ENTITY> mergeChildren(final Collection<DTO> dtos, final Collection<ENTITY> entities, final Function<ENTITY, Long> idSupplier) {
    return this.addModifyRemove(
        entities,
        this.mergeCollections(dtos, entities),
        idSupplier
    );
  }

  /**
   * Only used internally.<p> - remove, what is no longer needed<p> - modify, what has been changed<p> - add, what is
   * new
   *
   * @param entities original collection of children
   * @param mergeResult collection of merged children
   * @param idSupplier supplier to identify the elements (decide: modified, deprecated, new)
   *
   * @return the collection of entities
   */
  default Collection<ENTITY> addModifyRemove(final Collection<ENTITY> entities, final Collection<ENTITY> mergeResult, Function<ENTITY, Long> idSupplier) {
    final List<Long> modifiedIds = mergeResult.stream() // collect the id's of modified entities
        .map(idSupplier)
        .filter(Objects::nonNull)
        .toList(); // have id's

    if (!this.keepAllChildren()) {
      final List<ENTITY> toBeRemoved = entities.stream() // remove, what is no longer needed
          .filter(child -> !modifiedIds.contains(idSupplier.apply(child)))
          .toList();
      entities.removeAll(toBeRemoved);
    }

    final List<ENTITY> newEntities = mergeResult.stream()
        .filter(child -> Objects.isNull(idSupplier.apply(child)))
        .toList(); // no id's
    entities.addAll(newEntities); // add the newly created entities

    return entities;
  }

  /**
   * As DEFAULT, children which do not appear in the DTO's children-collection, will be removed.<p> May be overwritten.
   * Then a child will never be removed.
   *
   * @return if the children which won't appear in the DTO-collection should be removed from the ENTITY-collection
   */
  default boolean keepAllChildren() {
    return false;
  }

  /**
   * Merges a Collection of DTOs into a Collection of Entities (Childs)
   *
   * @param dtos Collection of DTOs
   * @param entities Collection of Entities (result)
   *
   * @return Collection of Entities which only contain the elements which where matched from the DTOs
   */
  default Collection<ENTITY> mergeCollections(final Collection<DTO> dtos, final Collection<ENTITY> entities) {
    final Map<DTO, ENTITY> matchingMap = this.matchMapping(dtos, entities);

    return matchingMap.entrySet()
        .stream()
        .map(entry -> this.mergeEntity(entry.getKey(), entry.getValue()))
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  /**
   * Map holding, all DTOs and the matching Entity (comparator), or a new Entity.
   *
   * @param dtos Collection of DTOs
   * @param entities Collection of Entities (result)
   *
   * @return Mapping DTO to Entity
   */
  default Map<DTO, ENTITY> matchMapping(final Collection<DTO> dtos, final Collection<ENTITY> entities) {
    final Map<DTO, ENTITY> result = new HashMap<>();

    if (dtos == null) {
      return result;
    }

    for (DTO dto : dtos) {
      result.put(dto,
          entities.stream()
              .filter(entity -> this.getComparator().apply(dto, entity))
              .filter(Objects::nonNull) // comparator might say: 'null' (if the comparing-field is not available)
              .findFirst() // MUST be UNIQUE - otherwise there is no way of keeping existing elements
              .orElse(this.getNewSupplier().get())
      );
    }
    return result;
  }
}

