package statisticservice.service.statistic;

import statisticservice.dto.StatisticDTO;

import java.util.List;

public interface StatisticService {
    void add(StatisticDTO statisticDTO);
    List<StatisticDTO> getAll();
}
