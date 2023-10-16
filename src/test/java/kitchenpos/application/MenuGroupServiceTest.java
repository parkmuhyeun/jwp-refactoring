package kitchenpos.application;

import kitchenpos.common.service.ServiceTest;
import kitchenpos.dao.MenuGroupDao;
import kitchenpos.domain.MenuGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class MenuGroupServiceTest extends ServiceTest {

    @Autowired
    private MenuGroupService menuGroupService;

    @Autowired
    private MenuGroupDao menuGroupDao;

    @Test
    void MenuGroup_을_생성할_수_있다() {
        //given
        final MenuGroup menuGroup = new MenuGroup("치킨");

        //when
        final MenuGroup createdMenuGroup = menuGroupService.create(menuGroup);

        //then
        assertThat(createdMenuGroup.getId()).isNotNull();
    }

    @Test
    void MenuGroup_을_조회할_수_있다() {
        //given
        final MenuGroup chickenGroup = new MenuGroup("치킨");
        final MenuGroup pizzaGroup = new MenuGroup("피자");
        menuGroupDao.save(chickenGroup);
        menuGroupDao.save(pizzaGroup);

        //when
        final var result = menuGroupService.list();

        //then
        assertThat(result).hasSize(2);
    }
}
