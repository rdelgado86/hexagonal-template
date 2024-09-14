package es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.config;

import es.rbailen.sample.hexagonalarchitecture.domain.service.ProductService;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.eventpublisher.ProductEventPublisherAdapter;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.logging.LoggerAdapter;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.ProductPersistenceAdapter;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.mapper.ProductPersistenceMapper;
import es.rbailen.sample.hexagonalarchitecture.infrastructure.adapters.output.persistence.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductPersistenceAdapter productPersistenceAdapter(ProductRepository productRepository, ProductPersistenceMapper productPersistenceMapper) {
        return new ProductPersistenceAdapter(productRepository, productPersistenceMapper);
    }

    @Bean
    public LoggerAdapter loggerAdapter() {
        return new LoggerAdapter();
    }

    @Bean
    public ProductEventPublisherAdapter productEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
        return new ProductEventPublisherAdapter(applicationEventPublisher);
    }

    @Bean
    public ProductService productService(ProductPersistenceAdapter productPersistenceAdapter, ProductEventPublisherAdapter productEventPublisherAdapter, LoggerAdapter loggerAdapter) {
        return new ProductService(productPersistenceAdapter, productEventPublisherAdapter,
            loggerAdapter);
    }

}
