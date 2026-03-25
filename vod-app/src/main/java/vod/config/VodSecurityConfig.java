package vod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class VodSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager detailsManager = new JdbcUserDetailsManager();
        detailsManager.setDataSource(dataSource);

        // Poprawione zapytanie – zwraca username, password, enabled
        detailsManager.setUsersByUsernameQuery(
                "SELECT username, password, true FROM user WHERE username=?"
        );

        // Role pozostają takie jak w bazie (ROLE_ADMIN itd.)
        detailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, role FROM role WHERE username=?"
        );

        return detailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // wyłączanie CSRF w nowym stylu
                .csrf(csrf -> csrf.disable())

                // konfiguracja autoryzacji w nowym stylu
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/webapi/books").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/webapi/libraries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/webapi/libraries/**").authenticated()
                        .anyRequest().permitAll()
                )

                // włączenie Basic Auth w nowym stylu
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}
