package es.rbailen.sample.hexagonalarchitecture.domain.service;

import es.rbailen.sample.hexagonalarchitecture.application.ports.input.GetProductUseCase;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.LoggerPort;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductEventPublisher;
import es.rbailen.sample.hexagonalarchitecture.application.ports.output.ProductOutputPort;
import es.rbailen.sample.hexagonalarchitecture.domain.event.ProductCreatedEvent;
import es.rbailen.sample.hexagonalarchitecture.domain.exception.ProductNotFound;
import es.rbailen.sample.hexagonalarchitecture.domain.model.Product;
import es.rbailen.sample.hexagonalarchitecture.application.ports.input.CreateProductUseCase;


public class ProductService implements CreateProductUseCase, GetProductUseCase {

    private final ProductOutputPort productOutputPort;

    private final ProductEventPublisher productEventPublisher;

    private final LoggerPort loggerPort;

    public ProductService(ProductOutputPort productOutputPort,
                          ProductEventPublisher productEventPublisher, LoggerPort loggerPort) {
        this.productOutputPort = productOutputPort;
        this.productEventPublisher = productEventPublisher;
        this.loggerPort = loggerPort;
    }

    @Override
    public Product createProduct(Product product) {
        loggerPort.info("Creating the product:\n "+product.toString());
        product = productOutputPort.saveProduct(product);
        productEventPublisher.publishProductCreatedEvent(new ProductCreatedEvent(product.getId()));
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return productOutputPort.getProductById(id).orElseThrow(() -> new ProductNotFound("Product not found with id " + id));
    }

}
