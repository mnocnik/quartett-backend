package work.nocnik.quartett.backend.database.merger.check;

import java.util.Collection;
import java.util.function.Function;

/**
 * Structure of Childs-Triple.
 *
 * @param <PARENT> type of the parent
 * @param <CHILD> type of the children
 */
public class VersionCheckerChildPair<PARENT, CHILD> {
  private final Function<PARENT, Collection<CHILD>> childsGetter; // OneToMany
  private final Function<PARENT, CHILD> childGetter; // OneToOne
  private final Function<CHILD, Long> entityId;
  private final Function<CHILD, Long> childVersion;

  private VersionCheckerChildPair(final Function<PARENT, Collection<CHILD>> childsGetter, final Function<PARENT, CHILD> childGetter, final Function<CHILD, Long> entityId, final Function<CHILD, Long> childVersion) {
    this.childsGetter = childsGetter;
    this.childGetter = childGetter;
    this.entityId = entityId;
    this.childVersion = childVersion;
  }

  @SuppressWarnings("rawtypes")
  public static <PARENT, CHILD> VersionCheckerChildPair oneToOne(final Function<PARENT, CHILD> childGetter, final Function<CHILD, Long> entityId, final Function<CHILD, Long> childVersion) {
    //noinspection unchecked
    return new VersionCheckerChildPair(null, childGetter, entityId, childVersion);
  }

  @SuppressWarnings("rawtypes")
  public static <PARENT, CHILD> VersionCheckerChildPair oneToMany(final Function<PARENT, Collection<CHILD>> childsGetter, final Function<CHILD, Long> entityId, final Function<CHILD, Long> childVersion) {
    //noinspection unchecked
    return new VersionCheckerChildPair(childsGetter, null, entityId, childVersion);
  }

  /**
   * for each id and version which is `null`, we add -1L
   *
   * @param parent parent-entity.
   *
   * @return the sum of version-values.
   */
  public Long inspect(final PARENT parent) {
    if (this.childsGetter != null) {
      return this.inspectSet(parent);
    }
    if (this.childGetter != null) {
      return this.inspectOne(parent);
    }
    return 0L;
  }

  /**
   * for each id and version which is `null`, we add -1L
   *
   * @param parent parent-entity.
   *
   * @return the sum of version-values.
   */
  public Long inspectSet(final PARENT parent) {
    final Collection<CHILD> childSet = this.childsGetter.apply(parent);
    if (childSet == null) {
      return 0L;
    }
    return childSet.stream()
        .mapToLong(this::calculateValue)
        .sum()
        ;
  }

  /**
   * for each id and version which is `null`, we add -1L
   *
   * @param parent parent-entity.
   *
   * @return the sum of version-values.
   */
  public Long inspectOne(final PARENT parent) {
    final CHILD child = this.childGetter.apply(parent);
    if (child == null) {
      return 0L;
    }
    return this.calculateValue(child);
  }

  private long calculateValue(final CHILD e) {
    final Long id = this.entityId.apply(e);
    final Long version = this.childVersion.apply(e);

    final long idResult = id == null ? -1L : id;
    final long versionResult = version == null ? -1L : version;

    return idResult + versionResult;
  }
}
