package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAcountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapper;
import org.sid.bankaccountservice.mappers.AccountMapperImpl;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAccountController {
  private BankAccountRepository bankAccountRepository;
  private AccountService accountService;
  private AccountMapperImpl accountMapper;
    public RestAccountController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapperImpl accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/BankAccounts")
    public List<BankAccount> bankAccounts(){
        return  bankAccountRepository.findAll();
    }
    @GetMapping("/BankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id){
        return  bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/BankAccounts")
    public BankAcountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        //if(bankAccount.getId()!=null) bankAccount.setId(UUID.randomUUID().toString());
        //return  bankAccountRepository.save(requestDTO);
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/BankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount){
        BankAccount ba=bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
        if(bankAccount.getBalance()!=null)ba.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedDate()!=null)ba.setCreatedDate(new Date());
        if(bankAccount.getType()!=null)ba.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) ba.setCurrency(bankAccount.getCurrency());
        return  bankAccountRepository.save(ba);
    }

    @DeleteMapping("BankAccounts/{id}")
    public void deleteBankAccounts(@PathVariable String id){
        bankAccountRepository.deleteAllById(Collections.singleton(id));
    }
}
