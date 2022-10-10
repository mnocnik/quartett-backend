package work.nocnik.quartett.backend.factory;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLBeanFactory {

  @Bean
  RuntimeWiringConfigurer configurer() {
    GraphQLScalarType scalarType = ExtendedScalars.UUID;
    return (builder) -> builder.scalar(scalarType);
  }
}
