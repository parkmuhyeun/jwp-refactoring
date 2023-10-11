package kitchenpos.ui;

import io.restassured.RestAssured;
import kitchenpos.common.controller.ControllerTest;
import kitchenpos.dao.MenuGroupDao;
import kitchenpos.dao.ProductDao;
import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuGroup;
import kitchenpos.domain.MenuProduct;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

class MenuRestControllerTest extends ControllerTest {

    @Autowired
    private MenuGroupDao menuGroupDao;

    @Autowired
    private ProductDao productDao;

    @Test
    void Menu를_생성하면_201을_반환한다() {
        // given
        final Product product = productDao.save(new Product("디노 초코 케이크", new BigDecimal(25000)));
        final MenuProduct menuProduct = new MenuProduct(null, product.getId(), 1);
        final MenuGroup menuGroup = menuGroupDao.save(new MenuGroup("디노 디저트"));
        final Menu 메뉴 = new Menu("디노 케이크", new BigDecimal(25000),
                menuGroup.getId(), List.of(menuProduct));
        final var 요청_준비 = RestAssured.given()
                .body(메뉴)
                .contentType(JSON);

        // when
        final var 응답 = 요청_준비
                .when()
                .post("/api/menus");

        // then
        응답.then().assertThat().statusCode(CREATED.value());
    }

    @Test
    void Menu를_조회하면_200을_반환한다() {
        // given
        final var 요청_준비 = RestAssured.given()
                .contentType(JSON);

        // when
        final var 응답 = 요청_준비
                .when()
                .get("/api/menus");

        // then
        응답.then().assertThat().statusCode(OK.value());
    }
}
