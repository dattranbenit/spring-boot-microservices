package statisticservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import statisticservice.dto.StatisticDTO;
import statisticservice.service.statistic.StatisticService;


@RestController
public class StatisticController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticService statisticService;

    @PostMapping("/statistic")
    public StatisticDTO add(@RequestBody StatisticDTO statisticDTO) {
        logger.info("Add statistic");
//    	try {
//    	    Thread.sleep(10000);
//    	} catch (InterruptedException e) {
//    	    // TODO Auto-generated catch block
//    	    e.printStackTrace();
//    	}
        statisticService.add(statisticDTO);
        return statisticDTO;
    }

    @GetMapping("/statistics")
    public List<StatisticDTO> getAll() {
        logger.info("Get all statistic");
        return statisticService.getAll();
    }
}
