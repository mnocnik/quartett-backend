package work.nocnik.cards.angularbackend.controller;

import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
//@Controller
//@RestController
//@RequestMapping(path = "/graphQL", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GraphQLController {
  private static final String AUTHORIZATION = "authorization";

  //  private final ExecutionGraphQlService executionGraphQlService;
//  private final GraphQL graphQL;
  private final GraphQL graphQL;

  @PostMapping
  public ResponseEntity<Map<String, Object>> graphQlQuery(@RequestHeader final Map<String, Object> headerMap, @RequestBody final Map<String, Object> queryMap) {
    final GraphExecution graphExecution = GraphExecution.of(queryMap);
    final ExecutionInput executionInput = graphExecution.toExecutionInput();
    return ResponseEntity.ok(Map.of());
  }
//
//  @PostMapping
//  @ResponseStatus
//  @ResponseBody
//  public ResponseEntity<Map<String, Object>> graphQlQuery(@RequestHeader final Map<String, Object> headerMap, @RequestBody final Map<String, Object> queryMap) {
//    final GraphExecution graphExecution = GraphExecution.of(queryMap)
//        .withContext(headerMap.get(AUTHORIZATION));
//    final ExecutionInput executionInput = graphExecution.toExecutionInput();
//    final ExecutionResult executionResult = this.graphQL.execute(executionInput);
//    log.trace("executed graphQLQueryString {}", executionInput.getQuery());
//
//    final Map<String, Object> executionResultSpecification = executionResult.toSpecification();
//    if (executionResultSpecification.containsKey("errors")) {
//      throw new GraphQueryException(queryMap, executionResultSpecification);
//    }
//
//    log.trace("Success: {}", graphExecution.getQuery());
//
//    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(executionResult.toSpecification());
//  }
}
