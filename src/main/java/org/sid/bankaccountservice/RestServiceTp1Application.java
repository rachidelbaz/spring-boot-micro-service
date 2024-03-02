package org.sid.bankaccountservice;

import org.sid.bankaccountservice.entities.BankAccount;
import org.sid.bankaccountservice.entities.Customer;
import org.sid.bankaccountservice.enums.AccountType;
import org.sid.bankaccountservice.repositories.BankAccountRepository;
import org.sid.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class RestServiceTp1Application {

    private  CustomerRepository customerRepository;
    public static void main(String[] args) {
        SpringApplication.run(RestServiceTp1Application.class, args);
    }

    public RestServiceTp1Application() {
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository,CustomerRepository customerRepository){
        return args -> {
            Stream.of("Mohamed","Rachid","Yassine","Hanane","Hassan").forEach(
                    c-> {
                        Customer customer = Customer.builder().name(c).build();
                        customerRepository.save(customer);
                    });
            customerRepository.findAll().forEach(c -> {
                for (int i=0;i<10;i++){
                    BankAccount bankAccount=BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .balance(10000+Math.random()*90000)
                            .createdDate(new Date())
                            .currency("MAD")
                            .customer(c)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }
            });
        };
    }
}
