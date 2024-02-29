package org.sid.bankaccountservice.mappers;
import org.sid.bankaccountservice.dto.BankAcountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;

public interface AccountMapper {
   public BankAcountResponseDTO fromBankAccount(BankAccount bankAccount);
}
