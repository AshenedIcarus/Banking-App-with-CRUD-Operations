package practice.projects.banking.mapper;

import practice.projects.banking.dto.AccountsDto;
import practice.projects.banking.entity.Accounts;

public class AccountsMapper {
    public static Accounts mapToAccounts(AccountsDto accountsDto)
    {
        Accounts account= new Accounts(
                accountsDto.getId(),
                accountsDto.getOwnerName(),
                accountsDto.getBalance()
        );
        return account;
    }

    public static AccountsDto mapToAccountsDto(Accounts account)
    {
        AccountsDto accountDto= new AccountsDto(
                account.getId(),
                account.getOwnerName(),
                account.getBalance()
        );

        return accountDto;
    }
}
