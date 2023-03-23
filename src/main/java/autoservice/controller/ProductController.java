package autoservice.controller;

import autoservice.model.Product;
import autoservice.model.dto.request.ProductRequestDto;
import autoservice.model.dto.response.ProductResponseDto;
import autoservice.service.ProductService;
import autoservice.service.mapper.impl.ProductMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create Product")
    ProductResponseDto save(@RequestBody
                            @Validated
                            @ApiParam(value = "Product parameters")
                            ProductRequestDto productRequestDto) {
        Product product = productService.saveOrUpdate(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Product")
    ProductResponseDto update(@PathVariable
                              @ApiParam(value = "Product ID") Long id,
                              @RequestBody
                              @Validated
                              @ApiParam(value = "Product parameters")
                              ProductRequestDto productRequestDto) {
        Product product = productService.saveOrUpdate(productMapper.toModel(productRequestDto));
        product.setId(id);
        return productMapper.toDto(product);
    }
}
