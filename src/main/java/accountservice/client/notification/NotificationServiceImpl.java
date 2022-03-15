package accountservice.client.notification;

import accountservice.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class NotificationServiceImpl implements NotificationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendNotification(MessageDTO messageDTO) {
        // fallback
        logger.error("Notification Service is slow");
    }
}