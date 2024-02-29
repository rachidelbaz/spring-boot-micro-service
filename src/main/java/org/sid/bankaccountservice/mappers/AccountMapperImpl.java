package org.sid.bankaccountservice.mappers;

import org.sid.bankaccountservice.dto.BankAcountResponseDTO;
import org.sid.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl {
    public BankAcountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAcountResponseDTO bankAcountResponseDTO=new BankAcountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAcountResponseDTO);
        return  bankAcountResponseDTO;
    }
}
