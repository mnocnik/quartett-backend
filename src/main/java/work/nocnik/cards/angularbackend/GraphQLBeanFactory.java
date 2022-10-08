package work.nocnik.cards.angularbackend;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
@RequiredArgsConstructor
public class GraphQLBeanFactory {
  @Bean
  public GraphQLSchema graphQL() {
    return GraphQLSchema.newSchema().build();
  }

  @Bean
  public GraphQL graphQL(final GraphQLSchema graphQLSchema) {
    return GraphQL.newGraphQL(graphQLSchema)
        .build();
  }
}
