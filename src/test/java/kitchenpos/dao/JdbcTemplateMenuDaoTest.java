package kitchenpos.dao;

import kitchenpos.domain.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcTemplateMenuDaoTest {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplateMenuDao jdbcTemplateMenuDao;

    @BeforeEach
    void setUp() {
        jdbcTemplateMenuDao = new JdbcTemplateMenuDao(dataSource);
    }

    @Test
    void saveAndFindById() {
        //given
        final Menu menu = new Menu("디노통구이", new BigDecimal(20000), 2L, null);

        // when
        jdbcTemplateMenuDao.save(menu);

        //then
        assertThat(jdbcTemplateMenuDao.findById(menu.getId())).isNotNull();
    }

    @Test
    void findAll() {
        //when
        final List<Menu> result = jdbcTemplateMenuDao.findAll();

        //then
        assertThat(result).hasSize(6);
    }

    @Test
    void countByIdIn() {
        //when
        final long count = jdbcTemplateMenuDao.countByIdIn(List.of(1L, 2L, 3L));

        //then
        assertThat(count).isEqualTo(3);
    }
}
