package kitchenpos.application;

import java.util.List;
import kitchenpos.dao.OrderDao;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.dao.OrderTableGroupDao;
import kitchenpos.domain.Order;
import kitchenpos.domain.OrderTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderTableService {

    private final OrderDao orderDao;
    private final OrderTableDao orderTableDao;
    private final OrderTableGroupDao orderTableGroupDao;

    public OrderTableService(OrderDao orderDao, OrderTableDao orderTableDao,
                             OrderTableGroupDao orderTableGroupDao) {
        this.orderDao = orderDao;
        this.orderTableDao = orderTableDao;
        this.orderTableGroupDao = orderTableGroupDao;
    }

    @Transactional
    public OrderTable create(int numberOfGuests, boolean empty) {
        return orderTableDao.save(new OrderTable(numberOfGuests, empty));
    }

    public List<OrderTable> list() {
        return orderTableDao.findAll();
    }

    @Transactional
    public OrderTable changeEmpty(Long orderTableId) {
        OrderTable orderTable = search(orderTableId);
        if (!canEmpty(orderTable)) {
            throw new IllegalArgumentException();
        }
        orderTable.changeEmpty();
        return orderTable;
    }

    private boolean canEmpty(OrderTable orderTable) {
        List<Order> orders = orderDao.findByOrderTableId(orderTable.getId());
        boolean anyMatchStatusIsComplete = orders.stream()
                .noneMatch(Order::isCompletion);
        boolean existsOrderTableGroup = orderTableGroupDao.existsById(
                orderTable.getOrderTableGroupId());

        return !anyMatchStatusIsComplete && existsOrderTableGroup;
    }

    @Transactional
    public OrderTable changeNumberOfGuests(Long orderTableId, int numberOfGuests) {
        OrderTable orderTable = search(orderTableId);
        if (orderTable.isEmpty()) {
            throw new IllegalArgumentException();
        }
        orderTable.changeNumberOfGuests(numberOfGuests);
        return orderTableDao.save(orderTable);
    }

    public OrderTable search(long orderTableId) {
        return orderTableDao.findById(orderTableId)
                .orElseThrow(IllegalArgumentException::new);
    }
}