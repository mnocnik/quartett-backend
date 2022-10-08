package work.nocnik.cards.angularbackend.controller;

import graphql.ExecutionInput;
import graphql.schema.DataFetchingEnvironment;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Utility to parse GraphQL-Map<String, Object> and build the corresponding ExecutionInput.
 */
@Slf4j
public class GraphExecution {
  private static final String QUERY = "query";
  private static final String OPERATION_NAME = "operationName";
  private static final String ROOT = "root";
  private static final String CONTEXT = "context";
  private static final String VARIABLES = "variables";

  @Getter
  private String query;
  private String operationName;
  private String root;
  private Object context;
  private Map<String, Object> variables;

  public static GraphExecution of(final Map<String, Object> queryMap) {
    final GraphExecution result = new GraphExecution();
    result.query = asStringOrNull(queryMap.get(QUERY));
    result.operationName = asStringOrNull(queryMap.get(OPERATION_NAME));
    result.root = asStringOrNull(queryMap.get(ROOT));
    result.context = queryMap.get(CONTEXT);
    //noinspection unchecked
    result.variables = (Map<String, Object>) queryMap.get(VARIABLES);
    return result;
  }

  public GraphExecution withQuery(final String query) {
    final String value = asStringOrNull(query);
    if (value != null) { // keep the previous value, if the new value is null or empty
      this.query = value;
    }
    return this;
  }

  public GraphExecution withOperationName(final String operationName) {
    final String value = asStringOrNull(operationName);
    if (value != null) { // keep the previous value, if the new value is null or empty
      this.operationName = value;
    }
    return this;
  }

  public GraphExecution withRoot(final String root) {
    final String value = asStringOrNull(root);
    if (value != null) { // keep the previous value, if the new value is null or empty
      this.root = value;
    }
    return this;
  }

  public GraphExecution withContext(final Object context) {
    if (context != null) { // keep the previous value, if the new value is null
      this.context = context;
    }
    return this;
  }

  @SuppressWarnings("unused")
  public GraphExecution withVariables(final Map<String, Object> variables) {
    if (variables != null && !variables.isEmpty()) {
      this.variables = variables; // keep the previous value, if the new value is null or empty
    }
    return this;
  }

  private static String asStringOrNull(final Object object) {
    if (object == null) {
      return null;
    }
    final String string = String.valueOf(object);
    if (StringUtils.hasText(string)) {
      return string;
    }
    return null;
  }

  public ExecutionInput toExecutionInput() {
    final ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();
    if (this.query != null) {
      builder.query(this.query);
    }
    if (this.operationName != null) {
      builder.operationName(this.operationName);
    }
    if (this.root != null) {
      builder.root(this.root);
    }
    if (this.context != null) {
      builder.context(this.context);
    }
    if (this.variables != null) {
      builder.variables(this.variables);
    }
    return builder.build();
  }

  @SuppressWarnings("unused")
  public static String subjectOfEnvironment(final DataFetchingEnvironment environment) {
    if (environment == null) {
      throw new SecurityException("UN-Authorized User - Environment was null");
    }
    try {
      final Map<String, Object> context = environment.getContext();
      final Object principalObject = context.get("principal");
      if (principalObject instanceof Map) {
        //noinspection unchecked
        final Map<String, String> principal = (Map<String, String>) principalObject;
        final String principalName = String.valueOf(principal.get("username"));
        log.debug("using Principal {}", principalName);
        if (ObjectUtils.isEmpty(principalName)) {
          throw new SecurityException("UN-Authorized User - No Principal available (empty)");
        }
        return principalName;
      } else {
        throw new SecurityException("UN-Authorized User - No Principal available");
      }
    } catch (Exception e) {
      throw new SecurityException("UN-Authorized User - could not find Principal. Exception: " + e.getMessage());
    }
  }

  @SuppressWarnings("unused")
  public static String firebaseUserIdOfEnvironment(final DataFetchingEnvironment environment) {
    if (environment == null) {
      throw new SecurityException("UN-Authorized User - Environment was null");
    }
    final Map<String, Object> context = environment.getContext();
    return String.valueOf(context.get("firebaseUserId"));
  }
}
