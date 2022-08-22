package com.example.Spring_Security_Tutoiral.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.example.Spring_Security_Tutoiral.security.AppUserRole.admin;
import static com.example.Spring_Security_Tutoiral.security.AppUserRole.student;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    protected UserDetailsService userDetailsService()
    {
        UserDetails userStudent = User.builder()
                .username("amit")
                .password(passwordEncoder.encode("12345"))
                .roles(student.name())
                .build();

        UserDetails userAdmin = User.builder()
                .username("Smith")
                .password(passwordEncoder.encode("admin"))
                .roles(admin.name())
                .build();

        return new InMemoryUserDetailsManager(userStudent, userAdmin);
    }

}
