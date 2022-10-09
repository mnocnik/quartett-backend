package work.nocnik.cards.angularbackend.factory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * If any Converter need a ConversionService, we need to provide this ConverterBeanFactory.
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ConverterBeanFactory implements ApplicationListener<ContextRefreshedEvent> {
  private final ConversionService conversionService;
  private final List<Converter<?, ?>> converterSet;

  @Override
  public void onApplicationEvent(@NotNull final ContextRefreshedEvent event) {
    if (this.conversionService instanceof GenericConversionService) {
      this.converterSet.forEach(((GenericConversionService) this.conversionService)::addConverter);
      log.info("added {} project-converters", this.converterSet.size());
    } else {
      log.error("NOT USING ANY PROJECT CONVERTERS - conversionService of TYPE {}", this.conversionService.getClass().getName());
    }
  }
}
