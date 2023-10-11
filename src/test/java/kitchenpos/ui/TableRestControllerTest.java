package kitchenpos.ui;

import io.restassured.RestAssured;
import kitchenpos.application.TableService;
import kitchenpos.common.controller.ControllerTest;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.dao.TableGroupDao;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.TableGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static io.restassured.http.ContentType.JSON;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

class TableRestControllerTest extends ControllerTest {

    @Autowired
    private TableService tableService;

    @Autowired
    private TableGroupDao tableGroupDao;

    @Autowired
    private OrderTableDao orderTableDao;

    @Test
    void OrderTable을_생성하면_201을_반환한다() {
        final OrderTable 주문_테이블 = new OrderTable(null, 0, true);
        final var 요청_준비 = RestAssured.given()
                .body(주문_테이블)
                .contentType(JSON);

        // when
        final var 응답 = 요청_준비
                .when()
                .post("/api/tables");

        // then
        응답.then().assertThat().statusCode(CREATED.value());
    }

    @Test
    void OrderTable을_조회하면_200을_반환한다() {
        // given
        final var 요청_준비 = RestAssured.given()
                .contentType(JSON);

        // when
        final var 응답 = 요청_준비
                .when()
                .get("/api/tables");

        // then
        응답.then().assertThat().statusCode(OK.value());
    }

    @Test
    void OrderTable을_비우면_200을_반환한다() {
        final OrderTable 주문_테이블 = tableService.create(new OrderTable(null, 0, false));
        final var 요청_준비 = RestAssured.given()
                .body(new OrderTable(null, 0, true))
                .contentType(JSON);

        // when
        final var 응답 = 요청_준비
                .when()
                .put("/api/tables/" + 주문_테이블.getId() + "/empty");

        // then
        응답.then().assertThat().statusCode(OK.value());
    }

    @Test
    void OrderTable의_손님_수를_변경하면_200을_반환한다() {
        final TableGroup tableGroup = tableGroupDao.save(new TableGroup(LocalDateTime.now(), null));
        final OrderTable 주문_테이블 = orderTableDao.save(new OrderTable(tableGroup.getId(), 0, false));
        final var 요청_준비 = RestAssured.given()
                .body(new OrderTable(null, 0, false))
                .contentType(JSON);

        // when
        final var 응답 = 요청_준비
                .when()
                .put("/api/tables/" + 주문_테이블.getId() + "/number-of-guests");

        // then
        응답.then().assertThat().statusCode(OK.value());
    }
}
