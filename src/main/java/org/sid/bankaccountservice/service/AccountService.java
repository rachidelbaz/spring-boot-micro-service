package org.sid.bankaccountservice.service;

import org.sid.bankaccountservice.dto.BankAccountRequestDTO;
import org.sid.bankaccountservice.dto.BankAcountResponseDTO;

public interface AccountService {
    public BankAcountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
}
