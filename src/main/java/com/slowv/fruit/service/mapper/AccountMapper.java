package com.slowv.fruit.service.mapper;

import com.slowv.fruit.domain.Account;
import com.slowv.fruit.domain.enums.EAccountStatus;
import com.slowv.fruit.service.dto.AccountDto;
import com.slowv.fruit.util.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {DateUtils.class, EAccountStatus.class})
public interface AccountMapper {

    @Mapping(target = "statusCode", expression = "java(EAccountStatus.getStatusByValue(account.getStatus()).getNumber())")
    @Mapping(target = "statusLabel", expression = "java(EAccountStatus.getStatusByValue(account.getStatus()).getLabel())")
//    @Mapping(target = "createdAt", expression = "java(account.getCreatedDate())")
//    @Mapping(target = "updatedAt", expression = "java(account.getUpdatedDate())")
    AccountDto toDto(Account account);
}
