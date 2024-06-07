package practice.projects.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.projects.banking.entity.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
