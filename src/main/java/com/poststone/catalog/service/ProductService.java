package com.poststone.catalog.service;

import com.poststone.catalog.database.repository.ProductRepository;
import com.poststone.catalog.service.dto.ProductFullDto;
import com.poststone.catalog.service.dto.ProductListDto;
import com.poststone.catalog.service.mapper.ProductMapper;
import com.poststone.catalog.service.util.PredicateBuilder;
import com.poststone.catalog.service.vo.ProductFilter;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.poststone.catalog.database.entities.QProduct.*;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Cacheable("product")
    public Page<ProductListDto> findAll(ProductFilter filter, Pageable pageable) {
        return productRepository.findAll(buildPredicate(filter), pageable)
                .map(productMapper::toListDto);
    }

    @Cacheable("product")
    public Optional<ProductFullDto> findOne(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toFullDto);
    }

    private BooleanExpression buildPredicate(ProductFilter filter) {
        PredicateBuilder builder = new PredicateBuilder();
        builder.add(filter.getType(), product.type::eq);
        builder.add(filter.getLowerPrice(), product.price::goe);
        builder.add(filter.getHigherPrice(), product.price::loe);
        builder.add(filter.getMaterials(), product.materials.any()::in);
        return builder.getExpression();
    }
}
