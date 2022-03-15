package accountservice.client.statistic;

import accountservice.dto.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class StatisticServiceImpl implements StatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public StatisticDTO add(StatisticDTO statisticDTO) {
        logger.error("Fallback statistic add");
        return statisticDTO;
    }
}
