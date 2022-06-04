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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.security.Principal;
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
        messageDTO.setToName(accountDTO.getUsername());
        messageDTO.setSubject("Welcome");
        messageDTO.setContent("Microservices content");

        notificationService.sendNotification(messageDTO);
        return accountDTO;
    }

    @PreAuthorize("#oauth2.hasScope('read') && hasRole('PREMIUM_MEMBER')")
    @GetMapping("/accounts")
    public List<AccountDTO> getAll() {
        statisticService.add(new StatisticDTO("Get all accounts.", new Date()));
        return accountService.getAll();
    }

    @PreAuthorize("#oauth2.hasScope('read') && hasRole('MEMBER')")
    @PostAuthorize("returnObject.body.username == authentication.name || hasRole('PREMIUM_MEMBER')")
    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable(name = "id") Long id) {
        statisticService.add(new StatisticDTO("Account " + id + " is trying to be accessed.", new Date()));
        return Optional.of(new ResponseEntity<AccountDTO>(accountService.getOne(id), HttpStatus.OK)) // success tra ve status ok
                .orElse(new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND)); // fail tra ve status not found
    }

    @Secured("ROLE_PREMIUM_MEMBER")
    @DeleteMapping("/account/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        statisticService.add(new StatisticDTO("Account " + id + " is deleted.", new Date()));
        accountService.delete(id);
    }

    @PreAuthorize("#oauth2.hasScope('write') && hasRole('PREMIUM_MEMBER')")
    @PutMapping("/account")
    public void update(@RequestBody AccountDTO accountDTO) {
        statisticService.add(new StatisticDTO("Account " + accountDTO.getId() + " is updated.", new Date()));
        accountService.update(accountDTO);
    }

    @PreAuthorize("#oauth2.hasScope('read') && isAuthenticated()")
    @GetMapping("/me")
    public Principal me(Principal principal) {
        return principal;
    }
}
