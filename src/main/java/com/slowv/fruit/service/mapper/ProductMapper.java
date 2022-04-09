package com.slowv.fruit.service.mapper;

import com.slowv.fruit.domain.Product;
import com.slowv.fruit.domain.enums.EProductStatus;
import com.slowv.fruit.service.dto.ProductDto;
import com.slowv.fruit.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {EProductStatus.class, DateUtils.class})
public interface ProductMapper {

    @Mapping(target = "statusCode", expression = "java(EProductStatus.getStatusByValue(product.getStatus()).getNumber())")
    @Mapping(target = "statusLabel", expression = "java(EProductStatus.getStatusByValue(product.getStatus()).getLabel())")
    @Mapping(target = "createdAt", expression = "java(DateUtils.longToString(product.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(DateUtils.longToString(product.getUpdatedAt()))")
    @Mapping(target = "discountPrice", expression = "java(product.getPriceDiscount())")
    @Mapping(target = "newzz", expression = "java(product.isNew())")
    ProductDto toDto(Product product);
}
