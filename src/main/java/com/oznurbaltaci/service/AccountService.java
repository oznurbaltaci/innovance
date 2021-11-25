package com.oznurbaltaci.service;

import com.oznurbaltaci.exception.DatabaseException;
import com.oznurbaltaci.exception.ErrorCode;
import com.oznurbaltaci.exception.InnovanceServiceException;
import com.oznurbaltaci.mapper.AccountMapper;
import com.oznurbaltaci.model.request.AccountCreateRequest;
import com.oznurbaltaci.model.request.TransferAccountRequest;
import com.oznurbaltaci.model.response.AccountResponse;
import com.oznurbaltaci.model.response.TransferResponse;
import com.oznurbaltaci.persistence.entity.Account;
import com.oznurbaltaci.persistence.entity.User;
import com.oznurbaltaci.persistence.repository.AccountRepository;
import com.oznurbaltaci.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = DatabaseException.class)
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    public void createAccount(Long userId, AccountCreateRequest createRequest){

        User user = getUser(userId);

        Account account = Account.builder()
                .amount(createRequest.getAmount())
                .currency(createRequest.getCurrency()).build();

        accountRepository.save(account);
        user.getAccounts().add(account);

        userRepository.save(user);
    }

    public List<AccountResponse> allUserCount(Long userId){
        User user = getUser(userId);
       return accountMapper.toAccountResponse(user.getAccounts());

    }

    public void removeUserAccount(Long userId, Long accountId){
        User user = getUser(userId);
        user.getAccounts().remove(getUserAccount(user, accountId));

        userRepository.save(user);

    }

    public TransferResponse transferAccount(Long senderUserId, TransferAccountRequest transferAccountRequest){

        Account sender = getAccount(transferAccountRequest.getSenderAccountId());
        Account receiver = getAccount(transferAccountRequest.getReceiverAccountId());

        User user = getUser(senderUserId);

        //eger hesap kullaniciya ait degilse
        user.getAccounts().stream().filter(x -> x.getAccountId().equals(transferAccountRequest.getSenderAccountId())).findAny().orElseThrow(() -> new InnovanceServiceException(ErrorCode.USER_NOT_ACCOUNT_ERROR));
        if(!transferAccountRequest.getSenderCurrency().equals(receiver.getCurrency())) throw new InnovanceServiceException(ErrorCode.CURRENCY_ERROR);
        if(transferAccountRequest.getAmount() > sender.getAmount()) throw new InnovanceServiceException(ErrorCode.AMOUNT_ERROR);

        //gonderdigim hesap benim ise
        Optional<Account> sameUser = user.getAccounts().stream()
                .filter(x -> x.getAccountId().equals(transferAccountRequest.getReceiverAccountId())).findAny();

        receiver.setAmount(transferAccountRequest.getAmount() + receiver.getAmount());
        sender.setAmount(sender.getAmount()-transferAccountRequest.getAmount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        if(!sameUser.isPresent()){return  TransferResponse.builder().message(user.getName() + " " + user.getSurname()
                + " tarafindan " + transferAccountRequest.getAmount() + " " +  transferAccountRequest.getSenderCurrency() +
                " tarafiniza gecmistir.").build();}


        else { return TransferResponse.builder().build();}
    }

    public User getUser (Long userId){
        return  userRepository.findById(userId).orElseThrow(() -> new InnovanceServiceException(ErrorCode.USER_NOT_FOUND));
    }

    public Account getUserAccount (User user,Long accountId){
        return user.getAccounts().stream().filter(x -> x.getAccountId().equals(accountId))
                .findAny().orElseThrow(() -> new InnovanceServiceException(ErrorCode.USER_ACCOUNT_NOT_FOUND));
    }
    public Account getAccount (Long accountId){
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new InnovanceServiceException(ErrorCode.ACCOUNT_ERROR));
    }

}
