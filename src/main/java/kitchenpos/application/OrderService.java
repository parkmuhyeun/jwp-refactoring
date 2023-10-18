package kitchenpos.application;

import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderStatus;
import kitchenpos.domain.OrderTable;
import kitchenpos.domain.repository.MenuRepository;
import kitchenpos.domain.repository.OrderLineItemRepository;
import kitchenpos.domain.repository.OrderRepository;
import kitchenpos.domain.repository.OrderTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final OrderTableRepository orderTableRepository;

    public OrderService(
            MenuRepository menuRepository,
            OrderRepository orderRepository,
            OrderLineItemRepository orderLineItemRepository,
            OrderTableRepository orderTableRepository
    ) {
        this.menuRepository = menuRepository;
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.orderTableRepository = orderTableRepository;
    }

    public Long create(final List<Long> menuIds, final List<Integer> quantities, final Long orderTableId) {
        if (menuIds.size() != menuRepository.countByIdIn(menuIds)) {
            throw new IllegalArgumentException("메뉴가 존재하지 않습니다.");
        }

        final OrderTable orderTable = orderTableRepository.findById(orderTableId)
                .orElseThrow(() -> new IllegalStateException("주문 테이블이 존재하지 않습니다."));
        orderTable.validateIsNotEmpty();
        
        final Order order = orderRepository.save(new Order(orderTableId, OrderStatus.COOKING.name(), LocalDateTime.now()));
        saveOrderLineItems(menuIds, quantities, order);
        return order.getId();
    }

    private void saveOrderLineItems(List<Long> menuIds, List<Integer> quantities, Order order) {
        for (int index = 0; index < menuIds.size(); index++) {
            final Long menuId = menuIds.get(index);
            final Integer quantity = quantities.get(index);

            final OrderLineItem orderLineItem = new OrderLineItem(order.getId(), menuId, quantity);
            orderLineItemRepository.save(orderLineItem);
        }
    }

    @Transactional(readOnly = true)
    public List<Order> list() {
        return orderRepository.findAll();
    }

    public void changeOrderStatus(final Long orderId, final String status) {
        final Order savedOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
        if (Objects.equals(OrderStatus.COMPLETION.name(), savedOrder.getOrderStatus())) {
            throw new IllegalArgumentException("이미 완료된 주문입니다.");
        }
        final OrderStatus orderStatus = OrderStatus.valueOf(status);
        savedOrder.changeOrderStatus(orderStatus.name());
    }
}
