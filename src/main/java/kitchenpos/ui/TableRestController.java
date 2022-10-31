package kitchenpos.ui;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kitchenpos.application.TableService;
import kitchenpos.application.request.OrderTableRequest;
import kitchenpos.domain.OrderTable;

@RestController
@RequestMapping("/api/tables")
public class TableRestController {

    private final TableService tableService;

    public TableRestController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<OrderTable> create(@RequestBody final OrderTableRequest orderTable) {
        final OrderTable created = tableService.create(orderTable);
        final URI uri = URI.create("/api/tables/" + created.getId());
        return ResponseEntity.created(uri)
            .body(created);
    }

    @GetMapping
    public ResponseEntity<List<OrderTable>> list() {
        return ResponseEntity.ok()
            .body(tableService.list());
    }

    @PutMapping("/{orderTableId}/empty")
    public ResponseEntity<OrderTable> changeEmpty(
        @PathVariable final Long orderTableId,
        @RequestBody final OrderTableRequest orderTable) {
        return ResponseEntity.ok()
            .body(tableService.changeEmpty(orderTableId, orderTable));
    }

    @PutMapping("/{orderTableId}/number-of-guests")
    public ResponseEntity<OrderTable> changeNumberOfGuests(
        @PathVariable final Long orderTableId,
        @RequestBody final OrderTableRequest orderTable) {
        return ResponseEntity.ok()
            .body(tableService.changeNumberOfGuests(orderTableId, orderTable));
    }
}
