package kitchenpos.tablegroup.presentation;

import kitchenpos.tablegroup.application.TableGroupService;
import kitchenpos.tablegroup.presentation.dto.TableGroupCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TableGroupRestController {
    private final TableGroupService tableGroupService;

    public TableGroupRestController(final TableGroupService tableGroupService) {
        this.tableGroupService = tableGroupService;
    }

    @PostMapping("/api/table-groups")
    public ResponseEntity<Void> create(@RequestBody final TableGroupCreateRequest tableGroupCreateRequest) {
        final Long tableGroupId = tableGroupService.create(tableGroupCreateRequest.getTableIds());
        final URI uri = URI.create("/api/table-groups/" + tableGroupId);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/api/table-groups/{tableGroupId}")
    public ResponseEntity<Void> ungroup(@PathVariable final Long tableGroupId) {
        tableGroupService.ungroup(tableGroupId);
        return ResponseEntity.noContent().build();
    }
}
