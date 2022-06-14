package accountservice.client.statistic;

import accountservice.dto.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class StatisticServiceImpl implements StatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.oauth2.token.url}")
    private String tokenUrl;

    @Value("${client.statistic.client.id}")
    private String clientId;

    @Value("${client.statistic.client.secret}")
    private String clientSecret;

    @Value("${client.statistic.client.scopes}")
    private List<String> scopes;

    @Override
    public StatisticDTO add(StatisticDTO statisticDTO) {
        logger.error("Fallback statistic add");

        logger.warn(tokenUrl);
        logger.warn(clientId);
        logger.warn(clientSecret);
        logger.warn(scopes.get(0));
        return statisticDTO;
    }
}
