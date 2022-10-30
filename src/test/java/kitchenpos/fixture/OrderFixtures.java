package kitchenpos.fixture;

import kitchenpos.dto.request.OrderCreateRequest;
import kitchenpos.dto.request.OrderLineItemCreateRequest;

import java.util.List;

public class OrderFixtures {

    public static OrderCreateRequest createOrder(final Long orderTableId,
                                                 final List<OrderLineItemCreateRequest> orderLineItems) {
        return new OrderCreateRequest(orderTableId, orderLineItems);
    }

    public static OrderCreateRequest createOrder(final Long orderTableId, final String orderStatus,
                                                 final List<OrderLineItemCreateRequest> orderLineItems) {
        return new OrderCreateRequest(orderTableId, orderStatus, orderLineItems);
    }
}