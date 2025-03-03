package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Material;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.MaterialRequest;
import iesbelen.iterpolaris.dto.MaterialResponse;
import iesbelen.iterpolaris.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public MaterialResponse createMaterial(User user, MaterialRequest request) {
        Material material = Material.builder()
                .name(request.getName())
                .type(request.getType())
                .url(request.getUrl())
                .description(request.getDescription())
                .deleted(false)
                .user(user)
                .build();
        Material saved = materialRepository.save(material);
        return mapToResponse(saved);
    }

    public List<MaterialResponse> getAllMaterials(User user) {
        return materialRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public MaterialResponse getMaterialById(User user, Long materialId) {
        Material material = materialRepository.findByIdAndDeletedFalse(materialId)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        if (!material.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este material");
        }
        return mapToResponse(material);
    }

    public MaterialResponse updateMaterial(User user, Long materialId, MaterialRequest request) {
        Material material = materialRepository.findByIdAndDeletedFalse(materialId)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        if (!material.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este material");
        }

        material.setName(request.getName());
        material.setType(request.getType());
        material.setUrl(request.getUrl());
        material.setDescription(request.getDescription());

        Material updated = materialRepository.save(material);
        return mapToResponse(updated);
    }

    public void deleteMaterial(User user, Long materialId) {
        Material material = materialRepository.findByIdAndDeletedFalse(materialId)
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));
        if (!material.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este material");
        }
        material.setDeleted(true);
        materialRepository.save(material);
    }

    private MaterialResponse mapToResponse(Material material) {
        return MaterialResponse.builder()
                .id(material.getId())
                .name(material.getName())
                .type(material.getType())
                .url(material.getUrl())
                .description(material.getDescription())
                .userId(material.getUser().getId())
                .build();
    }
}