package practice.projects.banking.service.implement;

import org.springframework.stereotype.Service;
import practice.projects.banking.dto.AccountsDto;
import practice.projects.banking.entity.Accounts;
import practice.projects.banking.mapper.AccountsMapper;
import practice.projects.banking.repository.AccountsRepository;
import practice.projects.banking.service.AccountsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplement implements AccountsService {

    private AccountsRepository accountsRepository;

    public AccountServiceImplement(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @Override
    public AccountsDto createAccount(AccountsDto accountDto) {
        Accounts account= AccountsMapper.mapToAccounts(accountDto);
        Accounts savedAccount= accountsRepository.save(account);
        return AccountsMapper.mapToAccountsDto(savedAccount);
    }

    @Override
    public AccountsDto getAccountByID(Long id) {
        Accounts account= accountsRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Account does not exist"));

        return AccountsMapper.mapToAccountsDto(account);
    }

    @Override
    public AccountsDto deposit(Long id, double amount) {
        Accounts account= accountsRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Account does not exist"));

        double newBalance= account.getBalance()+ amount;
        account.setBalance(newBalance);
        Accounts savedAccount= accountsRepository.save(account);
        return AccountsMapper.mapToAccountsDto(savedAccount);
    }

    @Override
    public AccountsDto withdraw(Long id, double amount) {
        Accounts account= accountsRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Account does not exist"));

        if(amount>account.getBalance())
        {
            throw new RuntimeException("Insufficient Balance");
        }
        double newBalance= account.getBalance()- amount;
        account.setBalance(newBalance);
        Accounts savedAccount= accountsRepository.save(account);
        return AccountsMapper.mapToAccountsDto(savedAccount);
    }

    @Override
    public List<AccountsDto> getAllAccounts() {
        List<Accounts> accounts= accountsRepository.findAll();
        return accounts.stream().map((account)-> AccountsMapper.mapToAccountsDto(account)).
                collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Accounts account= accountsRepository.
                findById(id).
                orElseThrow(()-> new RuntimeException("Account does not exist"));

        accountsRepository.deleteById(id);
    }
}
