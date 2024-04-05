package pl.marekksiazek.GastroManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marekksiazek.GastroManager.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}
