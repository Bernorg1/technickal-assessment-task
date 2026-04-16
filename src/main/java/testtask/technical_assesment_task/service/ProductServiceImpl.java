package testtask.technical_assesment_task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testtask.technical_assesment_task.dto.ProducerDto;
import testtask.technical_assesment_task.dto.ProductAttributeDto;
import testtask.technical_assesment_task.dto.ProductRequestDto;
import testtask.technical_assesment_task.dto.ProductResponseDto;
import testtask.technical_assesment_task.entity.Producer;
import testtask.technical_assesment_task.entity.Product;
import testtask.technical_assesment_task.entity.ProductAttribute;
import testtask.technical_assesment_task.exception.ResourceNotFoundException;
import testtask.technical_assesment_task.repository.ProducerRepository;
import testtask.technical_assesment_task.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Producer producer = producerRepository.findById(requestDto.getProducerId())
                .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + requestDto.getProducerId()));

        Product product = Product.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .price(requestDto.getPrice())
                .producer(producer)
                .build();

        if (requestDto.getAttributes() != null) {
            requestDto.getAttributes().forEach(attrDto -> {
                ProductAttribute attribute = ProductAttribute.builder()
                        .name(attrDto.getName())
                        .value(attrDto.getValue())
                        .type(attrDto.getType())
                        .build();
                product.addAttribute(attribute);
            });
        }

        Product savedProduct = productRepository.save(product);
        return mapToResponseDto(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Producer producer = producerRepository.findById(requestDto.getProducerId())
                .orElseThrow(() -> new ResourceNotFoundException("Producer not found with id: " + requestDto.getProducerId()));

        product.setName(requestDto.getName());
        product.setDescription(requestDto.getDescription());
        product.setPrice(requestDto.getPrice());
        product.setProducer(producer);

        product.getAttributes().clear();

        if (requestDto.getAttributes() != null) {
            requestDto.getAttributes().forEach(attrDto -> {
                ProductAttribute attribute = ProductAttribute.builder()
                        .name(attrDto.getName())
                        .value(attrDto.getValue())
                        .type(attrDto.getType())
                        .build();
                product.addAttribute(attribute);
            });
        }

        Product updatedProduct = productRepository.save(product);
        return mapToResponseDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    private ProductResponseDto mapToResponseDto(Product product) {
        ProducerDto producerDto = ProducerDto.builder()
                .id(product.getProducer().getId())
                .name(product.getProducer().getName())
                .build();

        List<ProductAttributeDto> attributeDtos = product.getAttributes().stream()
                .map(attr -> ProductAttributeDto.builder()
                        .id(attr.getId())
                        .name(attr.getName())
                        .value(attr.getValue())
                        .type(attr.getType())
                        .build())
                .collect(Collectors.toList());

        return ProductResponseDto.builder()
                .id(product.getId())
                .producer(producerDto)
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .attributes(attributeDtos)
                .build();
    }
}

