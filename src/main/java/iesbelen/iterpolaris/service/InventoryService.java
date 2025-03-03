package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Gear;
import iesbelen.iterpolaris.domain.InventoryItem;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.InventoryItemRequest;
import iesbelen.iterpolaris.dto.InventoryItemResponse;
import iesbelen.iterpolaris.repository.GearRepository;
import iesbelen.iterpolaris.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;
    private final GearRepository gearRepository;

    public InventoryService(InventoryItemRepository inventoryItemRepository,
                            GearRepository gearRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.gearRepository = gearRepository;
    }

    public InventoryItemResponse addGearToUser(User user, InventoryItemRequest request) {
        Gear gear = gearRepository.findByIdAndDeletedFalse(request.getGearId())
                .orElseThrow(() -> new RuntimeException("Gear no encontrado"));

        InventoryItem item = InventoryItem.builder()
                .gear(gear)
                .remainingUses(request.getRemainingUses() != null ? request.getRemainingUses() : gear.getMaxUses())
                .acquiredAt(LocalDateTime.now())
                .deleted(false)
                .user(user)
                .build();

        InventoryItem saved = inventoryItemRepository.save(item);
        return mapToResponse(saved);
    }

    public List<InventoryItemResponse> getUserInventory(User user) {
        return inventoryItemRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public InventoryItemResponse getItemById(User user, Long itemId) {
        InventoryItem item = inventoryItemRepository.findByIdAndDeletedFalse(itemId)
                .orElseThrow(() -> new RuntimeException("Item de inventario no encontrado"));
        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este ítem");
        }
        return mapToResponse(item);
    }

    public InventoryItemResponse updateInventoryItem(User user, Long itemId, InventoryItemRequest request) {
        InventoryItem item = inventoryItemRepository.findByIdAndDeletedFalse(itemId)
                .orElseThrow(() -> new RuntimeException("Item de inventario no encontrado"));
        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este ítem");
        }

        if (request.getRemainingUses() != null) {
            item.setRemainingUses(request.getRemainingUses());
        }
        InventoryItem updated = inventoryItemRepository.save(item);
        return mapToResponse(updated);
    }

    public void deleteInventoryItem(User user, Long itemId) {
        InventoryItem item = inventoryItemRepository.findByIdAndDeletedFalse(itemId)
                .orElseThrow(() -> new RuntimeException("Item de inventario no encontrado"));
        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este ítem");
        }

        item.setDeleted(true);
        inventoryItemRepository.save(item);
    }

    private InventoryItemResponse mapToResponse(InventoryItem item) {
        return InventoryItemResponse.builder()
                .id(item.getId())
                .remainingUses(item.getRemainingUses())
                .acquiredAt(item.getAcquiredAt())
                .gearId(item.getGear().getId())
                .gearName(item.getGear().getName())
                .userId(item.getUser().getId())
                .build();
    }
}
