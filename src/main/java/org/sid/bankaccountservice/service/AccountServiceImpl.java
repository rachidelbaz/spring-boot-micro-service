package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAcountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.mappers.AccountMapperImpl;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    AccountMapperImpl accountMapper;
    @Override
    public BankAcountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount=BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .type(bankAccountRequestDTO.getType())
                .createdDate(new Date())
                .currency(bankAccountRequestDTO.getCurrency())
                .balance(bankAccountRequestDTO.getBalance())
                .build();
        BankAccount saveBankAccount =bankAccountRepository.save(bankAccount);
        //BankAcountResponseDTO bankAcountResponseDTO=BankAcountResponseDTO.builder()
        //        .id(saveBankAccount.getId())
        //        .balance(saveBankAccount.getBalance())
        //        .createdDate(saveBankAccount.getCreatedDate())
        //        .currency(saveBankAccount.getCurrency())
        //        .type(saveBankAccount.getType())
        //        .build();
        BankAcountResponseDTO bankAcountResponseDTO =accountMapper.fromBankAccount(saveBankAccount);
        return bankAcountResponseDTO;
    }
}
