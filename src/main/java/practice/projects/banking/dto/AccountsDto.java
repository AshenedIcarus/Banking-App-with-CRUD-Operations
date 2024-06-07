package practice.projects.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountsDto {
    private long id;
    private String ownerName;
    private double balance;
}
