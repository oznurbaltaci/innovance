package com.oznurbaltaci.web.controller;

import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import com.oznurbaltaci.model.request.UserCreateRequest;
import com.oznurbaltaci.model.request.UserUpdateRequest;
import com.oznurbaltaci.model.response.UserResponse;
import com.oznurbaltaci.security.JwtClaimsModel;
import com.oznurbaltaci.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtClaimsModel jwtClaimsModel;

    @GetMapping()
   public ResponseEntity<List<UserResponse>> getUsers(){
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserCreateRequest userCreateRequest){
        userService.createUser(userCreateRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{identityNumber}")
    public ResponseEntity<Void> updateUser(@PathVariable Long identityNumber, @RequestBody UserCreateRequest userCreateRequest){
        if(!jwtClaimsModel.getIdentityNumber().equals(identityNumber))
            throw new InnovanceServiceException(ErrorCode.UNAUTHORIZED);

        userService.updateUser(identityNumber, userCreateRequest);
            return new ResponseEntity<>(HttpStatus.OK);

    }
    @PutMapping("/{identityNumber}")
    public ResponseEntity<Void> updateUserWithPut(@PathVariable Long identityNumber, @RequestBody UserUpdateRequest userUpdateRequest){
        if(!jwtClaimsModel.getIdentityNumber().equals(identityNumber))
            throw new InnovanceServiceException(ErrorCode.UNAUTHORIZED);

        userService.updateUserWithPut(identityNumber, userUpdateRequest);
        return new ResponseEntity<>(HttpStatus.OK);

    }




}
