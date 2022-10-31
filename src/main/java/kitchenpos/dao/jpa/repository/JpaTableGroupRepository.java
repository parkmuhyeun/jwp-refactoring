package kitchenpos.dao.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kitchenpos.domain.TableGroup;

public interface JpaTableGroupRepository extends JpaRepository<TableGroup, Long> {
}
