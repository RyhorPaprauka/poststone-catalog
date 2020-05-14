package com.poststone.catalog.service.mapper;

import com.poststone.catalog.database.entities.Description;
import com.poststone.catalog.database.entities.Product;
import com.poststone.catalog.database.repository.DescriptionRepository;
import com.poststone.catalog.service.CurrencyService;
import com.poststone.catalog.service.dto.ProductFullDto;
import com.poststone.catalog.service.dto.ProductListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.UUID;

@Mapper
public abstract class ProductMapper {

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private CurrencyService currencyService;

    @Mapping(target = "price", expression = "java(convertPrice(product.getPrice()))")
    public abstract ProductListDto toListDto(Product product);

    @Mapping(target = "price", expression = "java(convertPrice(product.getPrice()))")
    @Mapping(target = "description", expression = "java(getDescription(product.getId()))")
    public abstract ProductFullDto toFullDto(Product product);

    Description getDescription(UUID id) {
        String locale = LocaleContextHolder.getLocale().toString();
        return descriptionRepository.findByProductIdAndLocale(id, locale)
                .orElse(descriptionRepository.findByProductIdAndLocale(id, Locale.getDefault().toString())
                        .orElse(new Description()));
    }

    Integer convertPrice(BigDecimal price) {
        BigDecimal rate = currencyService.getUSDRate();
        BigDecimal result = price.multiply(rate);
        return result.setScale(0, RoundingMode.UP).intValue();
    }
}
