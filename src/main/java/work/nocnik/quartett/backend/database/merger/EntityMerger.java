package work.nocnik.quartett.backend.database.merger;

import java.util.function.Supplier;

public interface EntityMerger<DTO, ENTITY> {

  /**
   * Used to merge one single DTO into one single ENTITY (Parent XOR Child)
   *
   * @param dto the DTO which contains the new data
   * @param entity the existing (or new) ENTITY
   *
   * @return the merged ENTITY
   */
  ENTITY mergeEntity(final DTO dto, final ENTITY entity);

  /**
   * Equals-Test with two Suppliers (check nulls, which never equals).
   *
   * @param dto supply the dto
   * @param entity supply the entity
   *
   * @return true, if they match, else: false
   */
  default Boolean compare(final Supplier<Object> dto, final Supplier<Object> entity) {
    if (dto == null || entity == null) {
      return false;
    }
    final Object dtoValue = dto.get();
    final Object entityValue = entity.get();
    if (dtoValue == null || entityValue == null) {
      return false;
    }
    return entityValue.equals(dtoValue);
  }
}
