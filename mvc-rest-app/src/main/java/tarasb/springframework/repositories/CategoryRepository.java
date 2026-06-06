package tarasb.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tarasb.springframework.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
