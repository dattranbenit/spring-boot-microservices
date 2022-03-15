package accountservice.client.notification;

import accountservice.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://localhost:8003", fallback = NotificationServiceImpl.class)
public interface NotificationService {

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody MessageDTO messageDTO);
}