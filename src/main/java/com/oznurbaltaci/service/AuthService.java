package com.oznurbaltaci.service;

import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import com.oznurbaltaci.model.TokenModel;
import com.oznurbaltaci.model.request.LoginRequest;
import com.oznurbaltaci.persistence.entity.User;
import com.oznurbaltaci.persistence.repository.UserRepository;
import com.oznurbaltaci.security.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtGenerator jwtGenerator;

    public TokenModel login(LoginRequest loginRequest){
        User user = userRepository.findByIdentityNumber(loginRequest.getIdentityNumber()).orElseThrow(() -> new InnovanceServiceException(ErrorCode.USER_NOT_FOUND));
      return  jwtGenerator.generate(user);
    }
}
