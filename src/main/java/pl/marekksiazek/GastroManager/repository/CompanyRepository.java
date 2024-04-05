package pl.marekksiazek.GastroManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marekksiazek.GastroManager.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
