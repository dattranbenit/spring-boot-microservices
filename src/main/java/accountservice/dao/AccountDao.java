package accountservice.dao;

import accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account, Long> {

}