package com.oznurbaltaci.web.controller;

import com.oznurbaltaci.model.request.AccountCreateRequest;
import com.oznurbaltaci.model.request.TransferAccountRequest;
import com.oznurbaltaci.model.response.AccountResponse;
import com.oznurbaltaci.model.response.TransferResponse;
import com.oznurbaltaci.security.JwtClaimsModel;
import com.oznurbaltaci.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final JwtClaimsModel jwtClaimsModel;


    @PostMapping()
    public ResponseEntity<Void> createAccount(@RequestBody AccountCreateRequest accountCreateRequest){
        accountService.createAccount(jwtClaimsModel.getUserId(), accountCreateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/transferAccount")
    public ResponseEntity<TransferResponse> transferAccount(@RequestBody TransferAccountRequest transferAccountRequest){
        return new ResponseEntity<>(accountService.transferAccount(jwtClaimsModel.getUserId(),transferAccountRequest),HttpStatus.OK);

    }
    @GetMapping()
    public ResponseEntity<List<AccountResponse>> allUserAccount(){
        return new ResponseEntity<>(accountService.allUserCount(jwtClaimsModel.getUserId()), HttpStatus.OK);
    }
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> removeUserAccount(@PathVariable("accountId") Long accountId){
        accountService.removeUserAccount(jwtClaimsModel.getUserId(),accountId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
