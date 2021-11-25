package com.oznurbaltaci.mapper;

import com.oznurbaltaci.model.response.AccountResponse;
import com.oznurbaltaci.model.response.UserResponse;
import com.oznurbaltaci.persistence.entity.Account;
import com.oznurbaltaci.persistence.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<AccountResponse> toAccountResponse(List<Account> account);
}
