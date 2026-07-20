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
                        .requestMatchers("/", "/home", "/login", "/membresia/**", "/persona/**").permitAll()
                        .anyRequest().authenticated()
                )
                // Configurar el formulario de Login personalizado
                .formLogin(form -> form
                        .loginPage("/login")                           // Página de login personalizada
                        .loginProcessingUrl("/login")                  // URL donde se procesa el login
                        .defaultSuccessUrl("/membresia", true)         // Redirigir después del login exitoso
                        .failureUrl("/login?error")                    // Redirigir si falla el login
                        .usernameParameter("username")                 // Nombre del parámetro del usuario
                        .passwordParameter("password")                 // Nombre del parámetro de contraseña
                        .permitAll()
                )
                // Configurar el Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")                          // URL para logout
                        .logoutSuccessUrl("/login?logout")             // Redirigir después del logout
                        .permitAll()
                )
                .csrf().disable();  // Desactivar CSRF para pruebas (habilitar en producción)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}