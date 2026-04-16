package testtask.technical_assesment_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private ProducerDto producer;
    private String name;
    private String description;
    private BigDecimal price;
    private List<ProductAttributeDto> attributes;
}

