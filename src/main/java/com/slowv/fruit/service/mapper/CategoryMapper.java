package com.slowv.fruit.service.mapper;

import com.slowv.fruit.domain.Category;
import com.slowv.fruit.domain.enums.ECategoryStatus;
import com.slowv.fruit.service.dto.CategoryDto;
import com.slowv.fruit.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {ECategoryStatus.class, DateUtils.class})
public interface CategoryMapper {
    @Mapping(target = "statusCode", expression = "java(ECategoryStatus.getStatusByValue(category.getStatus()).getNumber())")
    @Mapping(target = "statusLabel", expression = "java(ECategoryStatus.getStatusByValue(category.getStatus()).getLabel())")
    @Mapping(target = "createdAt", expression = "java(DateUtils.longToString(category.getCreatedAt()))")
    @Mapping(target = "updatedAt", expression = "java(DateUtils.longToString(category.getUpdatedAt()))")
    CategoryDto toDto(Category category);
}
