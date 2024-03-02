package org.sid.bankaccountservice.web;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAcountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.sid.bankaccountservice.service.AccountServiceImpl;
import org.sid.bankaccountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    public BankAccountGraphQLController(AccountServiceImpl accountServiceImpl) {
        this.accountService = accountServiceImpl;
    }

    @QueryMapping
    public List<BankAccount> accountList(){
        return  bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAccount bankAccountById(@Argument String id){
        return  bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found",id)));
    }
    @MutationMapping
    public BankAcountResponseDTO addAccount(@Argument BankAccountRequestDTO requestDTO){
        //if(bankAccount.getId()!=null) bankAccount.setId(UUID.randomUUID().toString());
        //return  bankAccountRepository.save(requestDTO);
        return accountService.addAccount(requestDTO);
    }

    @MutationMapping
    public BankAcountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return  accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return  true;
    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }
}
