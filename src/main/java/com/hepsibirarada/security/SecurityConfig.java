package com.hepsibirarada.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from customer where username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select c.username, a.authority_name " +
                        "from customer c " +
                        "join customer_authority ca on c.id = ca.customer_id " +
                        "join authority a on ca.authority_id = a.id " +
                        "where c.username = ?"
        );


        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.POST,"/customer").permitAll()
                        .requestMatchers("/customer").hasRole("ADMIN")
                        .requestMatchers("/customer/bulk").hasRole("ADMIN")
                        .requestMatchers("/category/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/product").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/product").hasRole("ADMIN")
                        .requestMatchers("/order/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers(HttpMethod.GET).hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
        );
        //use basic authentication
        http.httpBasic(Customizer.withDefaults());

        //disable cross site request forgery
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
