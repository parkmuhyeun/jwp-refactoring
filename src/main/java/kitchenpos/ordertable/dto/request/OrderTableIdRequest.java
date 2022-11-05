package kitchenpos.ordertable.dto.request;

import kitchenpos.ordertable.domain.OrderTable;

public class OrderTableIdRequest {

    private Long id;

    private OrderTableIdRequest() {
    }

    public OrderTableIdRequest(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public OrderTable toEntity() {
        return new OrderTable(id);
    }
}