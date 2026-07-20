package episs.unaj.com.SRCAG.Security; // <-- Asegúrate de que coincida con tu estructura de paquetes

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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        .requestMatchers("/", "/home", "/membresia/**", "/persona/**").permitAll() // <-- Agregadas las rutas del Home
                        // 3. Cualquier otra ruta que agregues en el futuro requerirá iniciar sesión
                        .anyRequest().authenticated()
                )
                // 4. Configurar el formulario de Login por defecto de Spring Security
                .formLogin(form -> form
                        .defaultSuccessUrl("/membresia", true) // Redirigir aquí automáticamente al iniciar sesión
                        .permitAll()
                )
                // 5. Configurar el Logout (Cerrar sesión)
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}