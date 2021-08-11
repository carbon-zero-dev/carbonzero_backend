package com.carbonzero.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carbonzero.domain.Product;
import com.carbonzero.dto.ProductRequestData;
import com.carbonzero.error.ProductNotFoundException;
import com.carbonzero.repository.ProductRepository;
import com.github.dozermapper.core.Mapper;

@Service
public class ProductServiceImpl implements ProductService {

    private final Mapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(Mapper dozerMapper, ProductRepository productRepository) {
        this.mapper = dozerMapper;
        this.productRepository = productRepository;
    }

    /**
     * 상품을 생성한다.
     * @param productRequestData
     * @return 생성된 상품
     */
    @Override
    public Product createProduct(ProductRequestData productRequestData) {

        Product product = mapper.map(productRequestData, Product.class);

        return productRepository.save(product);
    }

    /**
     * 상품 목록을 반환한다.
     * @return 상품 목록
     */
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * 특정 상품을 반환한다.
     * @param id 상품 아이디
     * @return 조회할 상품
     */
    @Override
    public Product getProduct(Long id) {
        return findProduct(id);
    }

    @Override
    public Product updateProduct() {
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    /**
     * 특정한 상품을 조회하여 존재한다면 반환한다.
     * @param id 검색할 상품 아이디
     * @return 상품
     */
    public Product findProduct(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
