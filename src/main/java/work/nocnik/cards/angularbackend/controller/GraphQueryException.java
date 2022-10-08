package work.nocnik.cards.angularbackend.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GraphQueryException extends RuntimeException {

  private final Map<String, Object> queryMap;
  @Getter
  private final Map<String, Object> executionResultSpecification;

  public GraphQueryException(final Map<String, Object> queryMap, final Map<String, Object> executionResultSpecification) {
    super("Invalid GraphQL-Query");
    this.queryMap = queryMap;
    this.executionResultSpecification = executionResultSpecification;
  }

  public Map<String, Object> errorResponse() {
    final Map<String, Object> result = new HashMap<>(this.executionResultSpecification);
    result.put("query", this.queryMap.get("query"));
    return result;
  }
}
