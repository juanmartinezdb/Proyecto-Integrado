package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Template;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.TemplateRequest;
import iesbelen.iterpolaris.dto.TemplateResponse;
import iesbelen.iterpolaris.repository.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateService {

    private final TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    // Crear una nueva plantilla (ahora compatible con proyectos)
    public TemplateResponse createTemplate(User user, TemplateRequest request) {
        if (request.getCategory() == null || (!request.getCategory().equals("task")
                && !request.getCategory().equals("habit") && !request.getCategory().equals("project"))) {
            throw new RuntimeException("Categoría inválida para la plantilla.");
        }

        Template template = Template.builder()
                .name(request.getName())
                .description(request.getDescription())
                .energy(request.getEnergy())
                .points(request.getPoints())
                .priority(request.getPriority())
                .cycle(request.getCycle())
                .category(request.getCategory()) // Nueva propiedad para identificar si es de tareas, hábitos o proyectos
                .user(user)
                .deleted(false)
                .build();

        Template saved = templateRepository.save(template);
        return mapToResponse(saved);
    }

    // Obtener todas las plantillas del usuario con opción de filtrar por categoría
    public List<TemplateResponse> getAllTemplates(User user, String category) {
        if (category != null && !category.equals("task") && !category.equals("habit") && !category.equals("project")) {
            throw new RuntimeException("Categoría inválida para la plantilla.");
        }

        return templateRepository.findByUserAndDeletedFalse(user).stream()
                .filter(template -> category == null || template.getCategory().equals(category))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Eliminar (borrado lógico) una plantilla
    public void deleteTemplate(User user, Long templateId) {
        Template template = templateRepository.findByIdAndDeletedFalse(templateId)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));

        if (!template.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta plantilla");
        }

        template.setDeleted(true);
        templateRepository.save(template);
    }

    // Método auxiliar para mapear de entidad a DTO
    private TemplateResponse mapToResponse(Template template) {
        return TemplateResponse.builder()
                .id(template.getId())
                .name(template.getName())
                .description(template.getDescription())
                .energy(template.getEnergy())
                .points(template.getPoints())
                .priority(template.getPriority())
                .cycle(template.getCycle())
                .category(template.getCategory()) // Agregamos el campo de categoría
                .build();
    }
}
