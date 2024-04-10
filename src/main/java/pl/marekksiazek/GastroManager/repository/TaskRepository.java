package pl.marekksiazek.GastroManager.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.marekksiazek.GastroManager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
