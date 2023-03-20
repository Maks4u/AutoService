package autoservice.service.mapper;

import autoservice.model.Product;
import autoservice.model.dto.request.ProductRequestDto;
import autoservice.model.dto.response.ProductResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper implements Mapper<ProductRequestDto, ProductResponseDto, Product>{
    @Override
    public ProductResponseDto toDto(Product object) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(object.getId());
        productResponseDto.setName(object.getName());
        productResponseDto.setPrice(object.getPrice());
        return productResponseDto;
    }

    @Override
    public Product toModel(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        return product;
    }
}
