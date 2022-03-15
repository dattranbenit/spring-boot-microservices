package statisticservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import statisticservice.entity.Statistic;


public interface StatisticDao extends JpaRepository<Statistic, Long>{
}
