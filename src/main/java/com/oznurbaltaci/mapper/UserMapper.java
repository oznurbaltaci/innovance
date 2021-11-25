package com.oznurbaltaci.mapper;

import com.oznurbaltaci.model.request.UserCreateRequest;
import com.oznurbaltaci.model.response.UserResponse;
import com.oznurbaltaci.persistence.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<UserResponse> toUserResponse(List<User> user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User toUserFromRequest(UserCreateRequest userCreateRequest);
}
