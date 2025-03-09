package iesbelen.iterpolaris.security;

import iesbelen.iterpolaris.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig que:
 *  1) Usa Basic Auth (más cómodo para Postman).
 *  2) Evita el ciclo de dependencias inyectando por parámetro en los métodos @Bean.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * 1) Bean para codificar contraseñas.
     *    Tanto UserService como DaoAuthenticationProvider lo usarán.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 2) Bean que indica a Spring cómo cargar los usuarios y codificar contraseñas.
     *    No inyectamos nada por constructor, sino por parámetro para evitar el ciclo.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthProvider(UserService userService,
                                                     PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // userService implementa UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * 3) Cadena de filtros: configuramos seguridad HTTP:
     *    - /auth/register y /auth/login: acceso libre
     *    - /admin/** solo con rol ADMIN
     *    - el resto, autenticados.
     *    - Usa Basic Auth: .httpBasic(Customizer.withDefaults())
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           DaoAuthenticationProvider authProvider) throws Exception {

        http
                // Desactiva CSRF para poder probar con Postman sin tokens
                .csrf(csrf -> csrf.disable())

                // Añadimos nuestro authProvider para que use el userService y passwordEncoder
                .authenticationProvider(authProvider)

                // Rutas públicas y rutas restringidas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register", "/auth/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // Basic Auth: simple e ideal para Postman
                .httpBasic(Customizer.withDefaults())

                // Logout sencillo
                .logout(logout -> logout.permitAll());

        return http.build();
    }

}
