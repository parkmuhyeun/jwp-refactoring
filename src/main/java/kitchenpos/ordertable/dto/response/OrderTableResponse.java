package kitchenpos.ordertable.dto.response;

import kitchenpos.ordertable.domain.OrderTable;

public class OrderTableResponse {

    private final Long id;
    private final Long tableGroupId;
    private final int numberOfGuests;
    private final boolean empty;

    private OrderTableResponse(final Long id, final Long tableGroupId, final int numberOfGuests,
        final boolean empty) {
        this.id = id;
        this.tableGroupId = tableGroupId;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public Long getId() {
        return id;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public static OrderTableResponse from(final OrderTable orderTable) {
        return new OrderTableResponse(
            orderTable.getId(),
            orderTable.getTableGroupId(),
            orderTable.getNumberOfGuests(),
            orderTable.isEmpty()
        );
    }
}