package kitchenpos.menugroup;

import java.util.List;

public interface MenuGroupRepository {

    MenuGroup save(final MenuGroup entity);

    List<MenuGroup> findAll();

    boolean existsById(final Long id);
}
