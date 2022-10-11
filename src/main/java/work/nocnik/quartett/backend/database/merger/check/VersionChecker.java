package work.nocnik.quartett.backend.database.merger.check;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Framework, to decide, if an Entity was modified.<p> Takes into account, one Entity-type with some Sets of
 * Children.<p> Using the id- and version-fields (builds a sum over the parent and the childs).
 *
 * @param <PARENT> type of the Entity to be inspected
 */
public class VersionChecker<PARENT> {
  private final Function<PARENT, Long> parentId;
  private final Function<PARENT, Long> parentVersion;
  private final List<VersionCheckerChildPair> versionCheckerChildPairs = new ArrayList<>();

  private VersionChecker(final Function<PARENT, Long> parentId, final Function<PARENT, Long> parentVersion) {
    this.parentId = parentId;
    this.parentVersion = parentVersion;
  }

  public static <PARENT> VersionChecker<PARENT> build(final Function<PARENT, Long> parentId, final Function<PARENT, Long> parentVersion) {
    return new VersionChecker<>(parentId, parentVersion);
  }

  /**
   * for each id and version which is `null`, we add -1L
   *
   * @param entity the entity to inspect.
   *
   * @return the sum of version-values.
   */
  public Long inspect(final PARENT entity) {
    Long id = this.parentId.apply(entity);
    Long version = this.parentVersion.apply(entity);
    final long sumChildVersion = this.versionCheckerChildPairs.stream()
        .mapToLong(versionCheckerChildPair -> versionCheckerChildPair.inspect(entity))
        .sum();

    if (id == null) {
      id = -1L;
    }
    if (version == null) {
      version = -1L;
    }
    return id + version + sumChildVersion;
  }

  /**
   * ADD a triple for one Child.
   *
   * @param childGetter parents getter for the 'one' child
   * @param entityId getter of the 'one' child's id
   * @param childVersion getter of the 'one' child's version
   * @param <CHILD> type of the child
   *
   * @return SELF
   */
  public <CHILD> VersionChecker<PARENT> oneToOne(final Function<PARENT, CHILD> childGetter, final Function<CHILD, Long> entityId, final Function<CHILD, Long> childVersion) {
    if (childGetter != null && entityId != null && childVersion != null) {
      this.versionCheckerChildPairs.add(VersionCheckerChildPair.oneToOne(childGetter, entityId, childVersion));
    }
    return this;
  }

  /**
   * ADD a triple for one Childs-Set.
   *
   * @param childsGetter parents getter for the children
   * @param entityId getter of the childs id
   * @param childVersion getter of the childs version
   * @param <CHILD> type of the childs
   *
   * @return SELF
   */
  public <CHILD> VersionChecker<PARENT> oneToMany(final Function<PARENT, Collection<CHILD>> childsGetter, final Function<CHILD, Long> entityId, final Function<CHILD, Long> childVersion) {
    if (childsGetter != null && entityId != null && childVersion != null) {
      this.versionCheckerChildPairs.add(VersionCheckerChildPair.oneToMany(childsGetter, entityId, childVersion));
    }
    return this;
  }
}
