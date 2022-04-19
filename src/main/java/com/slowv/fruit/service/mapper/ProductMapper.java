package com.slowv.fruit.service.mapper;

import com.slowv.fruit.domain.Product;
import com.slowv.fruit.domain.enums.EProductStatus;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.service.dto.request.ProductUpdateRequest;
import com.slowv.fruit.util.CurrencyUtils;
import com.slowv.fruit.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        imports = {EProductStatus.class, DateUtils.class, CurrencyUtils.class}
)
public interface ProductMapper {

    @Mapping(target = "statusCode", expression = "java(EProductStatus.getStatusByValue(product.getStatus()).getNumber())")
    @Mapping(target = "statusLabel", expression = "java(EProductStatus.getStatusByValue(product.getStatus()).getLabel())")
    @Mapping(target = "discountPrice", expression = "java(product.getPriceDiscount())")
    @Mapping(target = "sale", expression = "java(product.getPerCent() > 0)")
    @Mapping(target = "newzz", expression = "java(product.isNew())")
    @Mapping(target = "unitPriceString", expression = "java(CurrencyUtils.format(product.getUnitPrice()))")
    @Mapping(target = "discountPriceString", expression = "java(CurrencyUtils.format(product.getPriceDiscount()))")
    ProductDto toDto(Product product);

    Product toEntity(ProductUpdateRequest dto);
}
