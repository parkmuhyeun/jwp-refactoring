package kitchenpos.dto;

public class OrderTableUpdateEmptyRequest {

    private final boolean empty;

    public OrderTableUpdateEmptyRequest(final boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }
}