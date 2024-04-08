package pl.marekksiazek.GastroManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marekksiazek.GastroManager.entity.DailyTask;

public interface DailyTaskRepository extends JpaRepository<DailyTask, Long> {
}
