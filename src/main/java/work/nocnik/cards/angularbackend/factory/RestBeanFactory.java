package work.nocnik.cards.angularbackend.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
public class RestBeanFactory {
  @Value("${lib.rest.timeout.connect:30000}")
  private int connectTimeout = 30000; // for rest-calls
  @Value("${lib.rest.timeout.read:30000}")
  private int readTimeout = 30000; // for rest-calls

  /**
   * This one is able to send 'PATCH'-requests
   */
  @Bean
  RestTemplate httpTemplate() {
    log.debug("creating httpTemplate: connectTimeout {} & readTimeout {}", this.connectTimeout, this.readTimeout);
    final RestTemplate restTemplate = new RestTemplate();

    final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(this.connectTimeout);
    requestFactory.setReadTimeout(this.readTimeout);
    restTemplate.setRequestFactory(requestFactory);

    return restTemplate;
  }

  @Bean
  Validator localValidatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }
}
