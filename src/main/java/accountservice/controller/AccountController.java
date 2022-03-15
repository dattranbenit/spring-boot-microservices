package accountservice.controller;

import accountservice.client.notification.NotificationService;
import accountservice.client.statistic.StatisticService;
import accountservice.dto.AccountDTO;
import accountservice.dto.MessageDTO;
import accountservice.dto.StatisticDTO;
import accountservice.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private StatisticService statisticService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
        statisticService.add(new StatisticDTO("Account " + accountDTO.getId() + " is created.", new Date()));
        accountService.add(accountDTO);

        // send notificaiton
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("memberd123@gmail.com");
        messageDTO.setTo(accountDTO.getEmail());// username is email
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome");
        messageDTO.setContent("Microservices content");

        notificationService.sendNotification(messageDTO);
        return accountDTO;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
        statisticService.add(new StatisticDTO("Get all accounts.", new Date()));
        return accountService.getAll();
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable(name = "id") Long id) {
        statisticService.add(new StatisticDTO("Account " + id + " is trying to be accessed.", new Date()));
        return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK)) // success tra ve status ok
                .orElse(new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND)); // fail tra ve status not found
    }

    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        statisticService.add(new StatisticDTO("Account " + id + " is deleted.", new Date()));
        accountService.delete(id);
    }

    @PutMapping("/account")
    public void update(@RequestBody AccountDTO accountDTO) {
        statisticService.add(new StatisticDTO("Account " + accountDTO.getId() + " is updated.", new Date()));
        accountService.update(accountDTO);
    }
}
