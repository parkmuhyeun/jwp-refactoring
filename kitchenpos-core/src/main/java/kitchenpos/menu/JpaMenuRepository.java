package kitchenpos.menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMenuRepository extends JpaRepository<Menu, Long>, MenuRepository {
}
