package testtask.technical_assesment_task.service;

import testtask.technical_assesment_task.dto.ProductRequestDto;
import testtask.technical_assesment_task.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto createProduct(ProductRequestDto requestDto);
    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto);
    void deleteProduct(Long id);
}

