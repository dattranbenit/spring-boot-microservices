package accountservice.service.account;

import accountservice.dao.AccountDao;
import accountservice.dto.AccountDTO;
import accountservice.entity.Account;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
class AccountServiceImpl implements AccountService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AccountDao accountDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void add(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
//        account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));

        accountDao.save(account);

        accountDTO.setId(account.getId());
    }

    @Override
    public void update(AccountDTO accountDTO) {
        Account account = accountDao.getById(accountDTO.getId());
        if (account != null) {
            modelMapper.typeMap(AccountDTO.class, Account.class)
                    .addMappings(mapper -> mapper.skip(Account::setPassword)).map(accountDTO, account);

            accountDao.save(account);
        }
    }

    @Override
    public void updatePassword(AccountDTO accountDTO) {
        Account account = accountDao.getById(accountDTO.getId());
        if (account != null) {
//            account.setPassword(new BCryptPasswordEncoder().encode(accountDTO.getPassword()));
            accountDao.save(account);
        }
    }

    @Override
    public void delete(Long id) {
        Account account = accountDao.getById(id);
        if (account != null) {
            accountDao.delete(account);
        }
    }

    @Override
    public List<AccountDTO> getAll() {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        logger.info("AccountService: Get All");

        accountDao.findAll().forEach((account) -> {
            accountDTOs.add(modelMapper.map(account, AccountDTO.class));
        });

        return accountDTOs;
    }

    @Override
    public AccountDTO getOne(Long id) {
        Account account = accountDao.getById(id);

        if (account != null) {
            return modelMapper.map(account, AccountDTO.class);
        }

        return null;
    }
}
