package com.slowv.fruit.service.mapper;

import com.slowv.fruit.domain.Collection;
import com.slowv.fruit.domain.enums.ECategoryStatus;
import com.slowv.fruit.service.dto.CollectionDto;
import com.slowv.fruit.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {DateUtils.class, ECategoryStatus.class})
public interface CollectionMapper {

    @Mapping(target = "statusCode", expression = "java(ECategoryStatus.getStatusByValue(collection.getStatus()).getNumber())")
    @Mapping(target = "statusLabel", expression = "java(ECategoryStatus.getStatusByValue(collection.getStatus()).getLabel())")
    CollectionDto toDto(Collection collection);
}
