package accountservice.client.statistic;

import accountservice.dto.StatisticDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

//@FeignClient(name = "statistic-service", url = "http://localhost:8004", fallback = StatisticServiceImpl.class)

@FeignClient(name = "statistic-service", fallback = StatisticServiceImpl.class)
public interface StatisticService {

    @PostMapping(value = "/statistic")
    StatisticDTO add(StatisticDTO statisticDTO);
}
