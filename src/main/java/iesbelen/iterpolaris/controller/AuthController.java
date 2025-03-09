package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.dto.RegisterRequest;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.UserDTO;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Registro de nuevos usuarios: /auth/register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    // Si usas formLogin, Spring Security maneja la ruta "/login" automáticamente.
    // Con Basic Auth, no hace falta. Pero puedes usar GET /auth/login para testear.
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Si ves esto, ya iniciaste sesión o no tienes seguridad HTTP Basic activada");
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal User user) {
        // Convertimos el usuario a DTO antes de devolverlo
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        return ResponseEntity.ok(userDTO);
    }
}
