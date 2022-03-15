package statisticservice.service.statistic;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import statisticservice.dao.StatisticDao;
import statisticservice.dto.StatisticDTO;
import statisticservice.entity.Statistic;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
class StatisticServiceImpl implements StatisticService {
    @Autowired
    StatisticDao statisticDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void add(StatisticDTO statisticDTO) {
        Statistic statistic = modelMapper.map(statisticDTO, Statistic.class);
        statisticDao.save(statistic);
    }

    @Override
    public List<StatisticDTO> getAll() {
        List<StatisticDTO> statisticDTOs = new ArrayList<>();
        statisticDao.findAll().forEach((statistic) -> {
            statisticDTOs.add(modelMapper.map(statistic, StatisticDTO.class));
        });

        return statisticDTOs;
    }
}