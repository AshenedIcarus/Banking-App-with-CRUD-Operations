package practice.projects.banking.service;

import practice.projects.banking.dto.AccountsDto;

import java.util.List;


public interface AccountsService {
    AccountsDto createAccount(AccountsDto accountsDto);

    AccountsDto getAccountByID(Long id);

    AccountsDto deposit(Long id, double amount);

    AccountsDto withdraw(Long id, double amount);

    List<AccountsDto> getAllAccounts();

    void deleteAccount(Long id);
}
