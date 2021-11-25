package com.oznurbaltaci.service;

import com.oznurbaltaci.exception.DatabaseException;
import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import com.oznurbaltaci.mapper.UserMapper;
import com.oznurbaltaci.model.request.UserCreateRequest;
import com.oznurbaltaci.model.request.UserUpdateRequest;
import com.oznurbaltaci.model.response.UserResponse;
import com.oznurbaltaci.persistence.entity.User;
import com.oznurbaltaci.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = DatabaseException.class)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> allUsers(){
        return userMapper.toUserResponse(userRepository.findAll());
    }

    public void createUser(UserCreateRequest userCreateRequest){

        userRepository.findByIdentityNumber(userCreateRequest.getIdentityNumber()).orElseThrow(() -> new InnovanceServiceException(ErrorCode.IDENTITY_ERROR));
        userRepository.save(userMapper.toUserFromRequest(userCreateRequest));
    }

    public void updateUser(Long identityNumber, UserCreateRequest userCreateRequest){
        User user = userRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new InnovanceServiceException(ErrorCode.USER_NOT_FOUND));
        if(!StringUtils.isBlank(userCreateRequest.getName()))
            user.setName(userCreateRequest.getName());
        if(!StringUtils.isBlank(userCreateRequest.getSurname()))
            user.setSurname(userCreateRequest.getSurname());
        if(!StringUtils.isBlank(userCreateRequest.getEmail()))
            user.setEmail(userCreateRequest.getEmail());
        userRepository.save(user);
    }

    public void updateUserWithPut(Long identityNumber, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new InnovanceServiceException(ErrorCode.USER_NOT_FOUND));

        User updatedUser = User.builder()
                .email(userUpdateRequest.getEmail())
                .name(userUpdateRequest.getName())
                .surname(userUpdateRequest.getSurname()).build();

        updatedUser.setUserId(user.getUserId());
        updatedUser.setIdentityNumber(user.getIdentityNumber());
        userRepository.save(updatedUser);

    }

}
