package practice.projects.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.projects.banking.dto.AccountsDto;
import practice.projects.banking.service.AccountsService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountsController {
    private AccountsService accountService;

    public AccountsController(AccountsService accountService) {
        this.accountService = accountService;
    }


    //Add Account REST API
    @PostMapping
    public ResponseEntity<AccountsDto> addAccount(@RequestBody AccountsDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountsDto> getAccountById(@PathVariable Long id)
    {
        AccountsDto accountDto= accountService.getAccountByID(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit REST API
    @PutMapping  ("/{id}/deposit")
    public ResponseEntity<AccountsDto> deposit(@PathVariable Long id,
                                               @RequestBody Map<String, Double> request)
    {
        Double amount= request.get("amount");
        AccountsDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw REST API
    @PutMapping ("/{id}/withdraw")
    public ResponseEntity<AccountsDto> withdraw(@PathVariable Long id,
                                                @RequestBody Map<String, Double> request)
    {
        Double amount = request.get("amount");
        AccountsDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get All Accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountsDto>> getAllAccounts()
    {
        List<AccountsDto> accountsDtos= accountService.getAllAccounts();
        return ResponseEntity.ok(accountsDtos);
    }

    //Delete Account REST API
    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount(id);

        return ResponseEntity.ok("Selected account has been deleted");
    }
}




