package pl.marekksiazek.GastroManager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig{

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT email, user_pwd, is_active FROM users WHERE email=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT email, role FROM users WHERE email=?");
        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/companies/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "api/users/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, AUTH_OWNER_PUT).hasAnyRole("ADMIN", "OWNER")
                        .requestMatchers(HttpMethod.POST, AUTH_ADMIN_POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, AUTH_ADMIN_GET).hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/users/**",
            "/api/users"
    };

    private static final String[] AUTH_ADMIN_GET = {
            "/api/companies",
            "/api/companies/{id}",
            "/admin",
            "/admin/**"
    };

    private static final String[] AUTH_ADMIN_POST = {
            "/api/users"
    };

    private static final String[] AUTH_OWNER_PUT = {
            "/api/companies/{id}",
            "/api/users/{id}"
    };

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}

