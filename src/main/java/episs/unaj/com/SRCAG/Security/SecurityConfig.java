package episs.unaj.com.SRCAG.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Definimos el encriptador de contraseñas requerido por Spring Security
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. Configuración de autorizaciones de rutas y login (Estilo del profesor)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permitir archivos estáticos (CSS, JS, imágenes) sin loguearse
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        // Restringir accesos según roles (ejemplo)
                        .requestMatchers("/persona/**").hasRole("ADMIN")
                        .requestMatchers("/membresia/**").hasRole("ADMIN")
                        .requestMatchers("/asistencia/**").hasAnyRole("ADMIN", "USER")
                        // Cualquier otra ruta requiere inicio de sesión
                        .anyRequest().authenticated()
                )
                // Configuración del formulario de Login por defecto
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // Redirige al index al iniciar sesión con éxito
                        .permitAll()
                )
                // Configuración del Logout
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}