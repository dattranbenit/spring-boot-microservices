package notificationservice.service.email;

import notificationservice.dto.MessageDTO;

public interface EmailService {
    void sendEmail(MessageDTO messageDTO);
}

