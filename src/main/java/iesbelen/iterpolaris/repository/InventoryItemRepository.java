package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.InventoryItem;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    Optional<InventoryItem> findByIdAndDeletedFalse(Long id);
    List<InventoryItem> findByUserAndDeletedFalse(User user);
}
