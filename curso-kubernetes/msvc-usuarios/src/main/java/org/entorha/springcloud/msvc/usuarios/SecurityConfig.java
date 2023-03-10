package org.entorha.springcloud.msvc.usuarios;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.authorizeRequests()
                .requestMatchers("/authorized").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario", "/usuario/{id}").hasAnyAuthority("SCOPE_read","SCOPE_write")
                .requestMatchers(HttpMethod.POST, "/usuario/").hasAuthority("SCOPE_write")
                .requestMatchers(HttpMethod.PUT, "/usuario/{id}").hasAuthority("SCOPE_write")
                .requestMatchers(HttpMethod.DELETE, "/usuario/{id}").hasAuthority("SCOPE_write")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Mantiene la sesion del usuario, no guarda la auth en la sesion http
                .and()
                .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/msvc-usuarios-client"))
                .oauth2Client(withDefaults())
                .oauth2ResourceServer().jwt();
         return http.build();
    }

}
