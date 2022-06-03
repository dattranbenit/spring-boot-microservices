package accountservice.dto;

import java.util.Set;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
